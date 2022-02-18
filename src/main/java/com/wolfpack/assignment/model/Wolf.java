package com.wolfpack.assignment.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table
public class Wolf {
    @Id
    @SequenceGenerator(
            name = "wolf_sequence",
            sequenceName = "wolf_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wolf_sequence"
    )
    private Long id;
    private String name;
    private String gender;
    private LocalDate dateOfBirth;

    // Coordinates of the current location
    private Float latitude;
    private Float longitude;

    public Wolf() {
    }

    public Wolf(Long id, String name, String gender, LocalDate dateOfBirth, Float latitude, Float longitude) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Wolf(String name, String gender, LocalDate dateOfBirth, Float latitude, Float longitude) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
