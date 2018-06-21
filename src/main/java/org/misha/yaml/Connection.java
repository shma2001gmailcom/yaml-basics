package org.misha.yaml;

public final class Connection {

    private String url;
    private String password;
    private String user;
    private int poolSize;

    String getUrl() {
        return url;
    }

    @SuppressWarnings("unused")
    public void setUrl(String url) {
        this.url = url;
    }

    String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    String getUser() {
        return user;
    }

    @SuppressWarnings("unused")
    public void setUser(String user) {
        this.user = user;
    }
}