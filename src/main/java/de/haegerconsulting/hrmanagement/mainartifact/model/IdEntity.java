package de.haegerconsulting.hrmanagement.mainartifact.model;

import javax.persistence.*;

@MappedSuperclass
public class IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private String id;

    public IdEntity() {
    }

    public IdEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
