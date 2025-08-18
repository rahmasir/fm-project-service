package org.rahmasir.fmprojectservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "skill")
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<Project> projects = new HashSet<>();

    public Skill(String name) {
        this.name = name;
    }
}
