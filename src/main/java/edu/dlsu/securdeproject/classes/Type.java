package edu.dlsu.securdeproject.classes;

import javax.persistence.Entity;

@Entity
public class Type {
    private Long id;
    private String typeName;

    public Type() {}

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
