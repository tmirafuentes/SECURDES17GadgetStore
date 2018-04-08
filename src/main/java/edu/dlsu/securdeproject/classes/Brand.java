package edu.dlsu.securdeproject.classes;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Brand {
    private Long brandId;
    private String brandName;
    private Set<Product> products;

    public Brand() {}

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @OneToMany(mappedBy = "productId")
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
