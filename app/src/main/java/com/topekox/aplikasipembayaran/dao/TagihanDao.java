package com.topekox.aplikasipembayaran.dao;

import com.topekox.aplikasipembayaran.domain.Tagihan;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TagihanDao {

    public List<Tagihan> daftarTagihan() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Tagihan> list = new ArrayList<>();

        try {
             list.add(new Tagihan("PLN PASCABAYAR", "00000001", "Uzumaki Naruto",
                    dateFormat.parse("2022-01-01"), dateFormat.parse("2022-01-20"),
                    new BigDecimal("100000.00")));

            list.add(new Tagihan("PDAM", "00000022", "Uciha Sasuke",
                    dateFormat.parse("2022-01-01"), dateFormat.parse("2022-01-20"),
                    new BigDecimal("80000.00")));

            list.add(new Tagihan("TELKOM", "090909", "Sikamaru Nara",
                    dateFormat.parse("2022-01-01"), dateFormat.parse("2022-01-20"),
                    new BigDecimal("420000.00")));

            list.add(new Tagihan("TELKOMSEL", "090909", "Abdul Kendeng Bin La Baco",
                    dateFormat.parse("2022-05-01"), dateFormat.parse("2022-05-20"),
                    new BigDecimal("420000.00")));
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

}
