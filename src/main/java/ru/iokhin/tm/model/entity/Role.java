package ru.iokhin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "role")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }
}

