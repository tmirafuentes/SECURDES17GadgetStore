package edu.dlsu.securdeproject.classes;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Brand {
    private Long brandId;
    private String brandName;

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
}
