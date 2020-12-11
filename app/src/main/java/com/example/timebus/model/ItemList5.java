package com.example.timebus.model;

public class ItemList5 {
    /*private String nomTerm;
    private String direccionTerm;
    private String imgEmp;*/

    private String nomEmpBus;
    private String imgEmp;
    private String preCiuDest1;


    public ItemList5(String nomEmpBus, String imgEmp,String preCiuDest1) {
        this.nomEmpBus = nomEmpBus;
        this.imgEmp = imgEmp;
        this.preCiuDest1=preCiuDest1;

    }

    public String getNomEmpBus() {
        return nomEmpBus;
    }

    public String getImgEmp() {
        return imgEmp;
    }
    public String getPreCiuDest1(){
        return preCiuDest1;
    }
}
