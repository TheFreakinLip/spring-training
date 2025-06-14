package com.training.spring.entity;

public class Product {

    private String productCode;
    private String productName;
    private Double unitPrice;

    public Product(String productCode, String productName, Double unitPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public Product() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
