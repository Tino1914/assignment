package com.wolfpack.assignment.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pack {
    @Id
    @SequenceGenerator(
            name = "pack_sequence",
            sequenceName = "pack_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pack_sequence"
    )
    private Long id;
    private String packName;

    @OneToMany
    private List<Wolf> wolves;


    public Pack() {
    }

    public Pack(String packName) {
        this.packName = packName;
        this.wolves = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public List<Wolf> getWolves() {
        return wolves;
    }

    public void setWolves(List<Wolf> wolves) {
        this.wolves = wolves;
    }

    public Pack addWolf(Wolf wolf) {
        this.wolves.add(wolf);
        return null;
    }

    public void removeWolf(long wolfId) {
        Wolf wolf = this.wolves.stream().filter(entry -> entry.getId() == wolfId).findFirst().orElse(null);
        if (wolf != null) this.wolves.remove(wolf);
    }

    @Override
    public String toString() {
        return "Pack{" +
                "id=" + id +
                ", packName='" + packName + '\'' +
                ", wolves=" + wolves +
                '}';
    }
}