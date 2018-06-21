package org.misha.yaml;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.join;

public class YamlRepoImpl implements Repository<User> {

    private static final String SELECT_USER_BY_LOGIN = "select * from USER where login = ?";
    private static final String SELECT_ALL_USERS = "select * from USER";
    private static final String INSERT_MAIL =
            "insert into USER " + "(login, password, age, roles, address) values (?, ?, ?, ?, ?)";
    private static final String COMMA = ",";
    private Factory<java.sql.Connection> factory;

    public YamlRepoImpl(Factory<java.sql.Connection> factory) {
        this.factory = factory;
    }

    public User findById(String login) {
        try (final java.sql.Connection con = factory.get();
             final PreparedStatement stmt = con.prepareStatement(SELECT_USER_BY_LOGIN)) {
            stmt.setString(1, login);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return assemble(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getCause());
        }
    }

    public List<User> findAll() {
        try (final java.sql.Connection con = factory.get(); Statement stmt = con.createStatement();
             final ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS)) {
            final List<User> result = new ArrayList<>();
            while (rs.next()) {
                result.add(assemble(rs));
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getCause());
        }
    }

    public void save(User value) {
        try (final Connection con = factory.get();
             final PreparedStatement stmt = con.prepareStatement(INSERT_MAIL)) {
            stmt.setString(1, value.getName());
            stmt.setString(2, value.getName());
            stmt.setInt(3, value.getAge());
            stmt.setString(4, join(value.getRoles(), COMMA));
            stmt.setString(5, disassemble(value.getAddress()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e.getCause());
        }
    }

    private String disassemble(final Map<String, String> address) {
        final StringBuilder sb = new StringBuilder();
        final int size = address.size();
        int i = 0;
        for (Map.Entry<String, String> e : address.entrySet()) {
            sb.append(e.getKey()).append(":").append(e.getValue()).append(i < size - 1 ? COMMA : EMPTY);
            ++i;
        }
        return sb.toString();
    }

    private User assemble(ResultSet rs) throws SQLException {
        final User.Builder builder = User.getBuilder();
        builder.addName(rs.getString("login")).
        addAge(rs.getInt("age"));
        mapAddress(rs, builder);
        mapRoles(rs, builder);
        return builder.build();
    }

    private void mapRoles(ResultSet rs, User.Builder builder) throws SQLException {
        final String rawRoles = rs.getString("roles");
        builder.addRoles(rawRoles == null ? new String[0] : rawRoles.split(COMMA));
    }

    private void mapAddress(final ResultSet rs, final User.Builder builder) throws SQLException {
        final String rawAddress = rs.getString("address");
        final String[] addressParts = rawAddress.split(COMMA);
        final Map<String, String> address = new HashMap<>();
        builder.addAddress(address);
        for (final String part : addressParts) {
            if (part != null) {
                final String[] partParts = part.split(":");
                address.put(partParts[0].trim(), partParts.length == 2 ? partParts[1].trim() : "NONE");
            }
        }
    }
}
