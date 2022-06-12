package com.topekox.aplikasipembayaran.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PembayaranClientResponse {

    private boolean success;

    @Override
    public String toString() {
        return "PembayaranClientResponse{" +
                "success=" + success +
                '}';
    }
}
