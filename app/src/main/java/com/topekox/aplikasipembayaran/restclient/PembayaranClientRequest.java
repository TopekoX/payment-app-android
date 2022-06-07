package com.topekox.aplikasipembayaran.restclient;

public class PembayaranClientRequest {

    private String username;
    private String password;

    public PembayaranClientRequest() {
    }

    public PembayaranClientRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static PembayaranClientRequestBuilder builder() {
        return new PembayaranClientRequestBuilder();
    }

    public static class PembayaranClientRequestBuilder {
        private String username;
        private String password;

        public PembayaranClientRequestBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public PembayaranClientRequestBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public PembayaranClientRequest build() {
            return new PembayaranClientRequest (username, password);
        }

    }
}
