package edu.dlsu.securdeproject.classes.dtos;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.jmx.export.annotation.ManagedNotification;

import javax.validation.constraints.NotNull;

public class ProductDto {
    @NotNull
    @NotEmpty
    private String productName;

    @NotNull
    @NotEmpty
    private double productPrice;

    @NotNull
    @NotEmpty
    private int productQuantity;

    @NotNull
    @NotEmpty
    private String productDescription;

    @NotNull
    @NotEmpty
    private String productBrand;

    @NotNull
    @NotEmpty
    private String productType;

    public ProductDto() {}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
