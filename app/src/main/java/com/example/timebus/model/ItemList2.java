package com.example.timebus.model;

import java.io.Serializable;

public class ItemList2 implements Serializable {

    private String nomEmpBus;
    private String imgEmp;
    private String preCiuDest1;
    private String preCiudDest2;
    private String preCiudDest3;
    private String preCiu;

    public ItemList2(String nomEmpBus, String imgEmp, String preCiuDest1, String preCiudDest2, String preCiudDest3, String preCiu) {
        this.nomEmpBus = nomEmpBus;
        this.imgEmp = imgEmp;
        this.preCiuDest1 = preCiuDest1;
        this.preCiudDest2 = preCiudDest2;
        this.preCiudDest3 = preCiudDest3;
        this.preCiu = preCiu;
    }

    public String getNomEmpBus() {
        return nomEmpBus;
    }

    public String getImgEmp() {
        return imgEmp;
    }

    public String getPreCiuDest1() {
        return preCiuDest1;
    }

    public String getPreCiudDest2() {
        return preCiudDest2;
    }

    public String getPreCiudDest3() {
        return preCiudDest3;
    }

    public String getPreCiu() {
        return preCiu;
    }
}
