package com.topekox.aplikasipembayaran.restclient;

public class PembayaranClientResponse {

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "PembayaranClientResponse{" +
                "success=" + success +
                '}';
    }
}
