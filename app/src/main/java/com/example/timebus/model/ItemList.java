package com.example.timebus.model;

import java.io.Serializable;

public class ItemList implements Serializable {

    private String nomTerm;
    private String direccionTerm;
    private String imgTerm;


    public ItemList(String nomTerm, String direccionTerm, String imgTerm) {
        this.nomTerm = nomTerm;
        this.direccionTerm = direccionTerm;
        this.imgTerm = imgTerm;
    }


    public String getNomTerm() {
        return nomTerm;
    }

    public String getDireccionTerm() {
        return direccionTerm;
    }

    public String getImgTerm() {
        return imgTerm;
    }
}
