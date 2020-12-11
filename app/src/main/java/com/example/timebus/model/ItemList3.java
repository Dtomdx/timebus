package com.example.timebus.model;

import java.io.Serializable;

public class ItemList3 implements Serializable {


    private String imgBus;
    private String horaSalBus;
    private String asientoDispBus;

    public ItemList3(String imgBus, String horaSalBus, String asientoDispBus) {
        this.imgBus = imgBus;
        this.horaSalBus = horaSalBus;
        this.asientoDispBus = asientoDispBus;
    }

    public String getImgBus() {
        return imgBus;
    }

    public String getHoraSalBus() {
        return horaSalBus;
    }

    public String getAsientoDispBus() {
        return asientoDispBus;
    }
}