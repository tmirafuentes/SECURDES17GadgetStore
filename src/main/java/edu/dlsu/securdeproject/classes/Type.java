package edu.dlsu.securdeproject.classes;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Type {
    private Long typeId;
    private String typeName;

    public Type() {}

    public Type(String typeName) {
        this.typeName = typeName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
