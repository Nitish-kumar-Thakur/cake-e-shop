package com.onlinebackery.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false,unique = true)
    @NotEmpty
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public Role(Integer id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public Role(){
        super();
    }
}
