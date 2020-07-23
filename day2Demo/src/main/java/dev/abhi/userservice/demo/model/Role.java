package dev.abhi.userservice.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
                                inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"))
    private List<Permission> permissions;

    //we don't need a join table here because it is done already in user class
    //default fetch type for many to many is lazy
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<User> users;

}
