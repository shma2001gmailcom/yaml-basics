package org.misha.yaml;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static java.lang.String.format;

public final class Configuration {
    private Connection connection;

    Connection getConnection() {
        return connection;
    }

    @SuppressWarnings("unused")
    public void setConnection(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return format("Connecting to database: %s\n", ToStringBuilder.reflectionToString(connection));
    }
}
