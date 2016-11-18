package de.zooplus.converter.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dragan Susak on 18-Nov-16.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    private Integer id;

    private String name;

    private String emaail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmaail() {
        return emaail;
    }

    public void setEmaail(String emaail) {
        this.emaail = emaail;
    }
}
