package com.demo.orders.model;

import java.io.Serializable;

public class MaterialMasterDataSource implements Serializable {

    private String company_code, plant_code, salesorg_code, material_code, material_name, material_category, uom, taxdetails, net_weight, returnProductQty, replaceQty, returnType;
    private byte[] imageMaterial;
    private boolean isChecked;

    public MaterialMasterDataSource(String company_code, String plant_code, String salesorg_code, String material_code,
                                    String material_name, String material_category, String uom, String taxdetails, String net_weight, byte[] imageMaterial) {
        this.company_code = company_code;
        this.plant_code = plant_code;
        this.salesorg_code = salesorg_code;
        this.material_code = material_code;
        this.material_name = material_name;
        this.material_category = material_category;
        this.uom = uom;
        this.taxdetails = taxdetails;
        this.net_weight = net_weight;
        this.imageMaterial = imageMaterial;
    }

    public MaterialMasterDataSource(String company_code, String plant_code, String salesorg_code, String material_code,
                                    String material_name, String material_category, String uom, String taxdetails, String net_weight,
                                    byte[] imageMaterial, String returnType) {
        this.company_code = company_code;
        this.plant_code = plant_code;
        this.salesorg_code = salesorg_code;
        this.material_code = material_code;
        this.material_name = material_name;
        this.material_category = material_category;
        this.uom = uom;
        this.taxdetails = taxdetails;
        this.net_weight = net_weight;
        this.imageMaterial = imageMaterial;
        this.returnType = returnType;
    }


    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReplaceQty() {
        return replaceQty;
    }

    public void setReplaceQty(String replaceQty) {
        this.replaceQty = replaceQty;
    }

    public String getReturnProductQty() {
        return returnProductQty;
    }

    public void setReturnProductQty(String returnProductQty) {
        this.returnProductQty = returnProductQty;
    }

    public byte[] getImageMaterial() {
        return imageMaterial;
    }

    public void setImageMaterial(byte[] imageMaterial) {
        this.imageMaterial = imageMaterial;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getPlant_code() {
        return plant_code;
    }

    public void setPlant_code(String plant_code) {
        this.plant_code = plant_code;
    }

    public String getSalesorg_code() {
        return salesorg_code;
    }

    public void setSalesorg_code(String salesorg_code) {
        this.salesorg_code = salesorg_code;
    }

    public String getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(String material_code) {
        this.material_code = material_code;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_category() {
        return material_category;
    }

    public void setMaterial_category(String material_category) {
        this.material_category = material_category;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getTaxdetails() {
        return taxdetails;
    }

    public void setTaxdetails(String taxdetails) {
        this.taxdetails = taxdetails;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }
}
