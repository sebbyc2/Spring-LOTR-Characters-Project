package com.qa.lotrcharactersproject.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Character")
public class LOTRCharacter {

    // Instance Variables

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private Integer age;

    @Column
    private String race;

    // Constructors
    public LOTRCharacter() {
    }

    public LOTRCharacter(String name, Integer age, String race) {
        this.name = name;
        this.age = age;
        this.race = race;
    }

    public LOTRCharacter(Long id, String name, Integer age, String race) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.race = race;
    }

    // Getters & setters

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    // Hashcode and Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LOTRCharacter)) return false;

        LOTRCharacter LOTRCharacter = (LOTRCharacter) o;

        if (!getId().equals(LOTRCharacter.getId())) return false;
        if (!getName().equals(LOTRCharacter.getName())) return false;
        if (!getAge().equals(LOTRCharacter.getAge())) return false;
        return getRace().equals(LOTRCharacter.getRace());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge().hashCode();
        result = 31 * result + getRace().hashCode();
        return result;
    }
}
