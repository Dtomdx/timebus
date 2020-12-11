package com.example.timebus.model;

import java.io.Serializable;

public class ItemList6 implements Serializable {


    private String ciuDest1;
    private String horaSalBus;
    private String preCiuDest1;
    private String asientoDispBus;

    private String ciuDest;
    private String preCiu;

    public ItemList6(String ciuDest1, String horaSalBus, String preCiuDest1, String asientoDispBus, String ciuDest, String preCiu) {
        this.ciuDest1 = ciuDest1;
        this.horaSalBus = horaSalBus;
        this.preCiuDest1 = preCiuDest1;
        this.asientoDispBus = asientoDispBus;
        this.ciuDest = ciuDest;
        this.preCiu = preCiu;
    }

    public String getCiuDest1() {
        return ciuDest1;
    }

    public String getHoraSalBus() {
        return horaSalBus;
    }

    public String getPreCiuDest1() {
        return preCiuDest1;
    }

    public String getAsientoDispBus() {
        return asientoDispBus;
    }

    public String getCiuDest() {
        return ciuDest;
    }

    public String getPreCiu() {
        return preCiu;
    }
}
