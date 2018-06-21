package org.misha.yaml;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public final class User {
    private String name;
    private int age;
    private Map<String, String> address;
    private String[] roles;

    String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    Map<String, String> getAddress() {
        return address;
    }

    private void setAddress(Map<String, String> address) {
        this.address = address;
    }

    String[] getRoles() {
        return roles;
    }

    private void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("age", age).append("address", address)
                                        .append("roles", roles).toString();
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private final User user;

        private Builder() {
            user = new User();
        }

        public Builder addName(final String name) {
            user.setName(name);
            return this;
        }

        public Builder addAge(final int age) {
            user.setAge(age);
            return this;
        }

        public Builder addAddress(final Map<String, String> address) {
            user.setAddress(address);
            return this;
        }

        @SafeVarargs
        public final Builder addRoles(final String... roles) {
            user.setRoles(roles);
            return this;
        }

        public User build() {
            return user;
        }


    }
}