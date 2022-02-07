package com.qa.lotrcharactersproject.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Character {

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
    private Long age;

    @Column
    private String race;

    // Constructors
    public Character() {
    }

    public Character(String name, Long age, String race) {
        this.name = name;
        this.age = age;
        this.race = race;
    }

    public Character(Long id, String name, Long age, String race) {
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
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
        if (!(o instanceof Character)) return false;

        Character character = (Character) o;

        if (!getId().equals(character.getId())) return false;
        if (!getName().equals(character.getName())) return false;
        if (!getAge().equals(character.getAge())) return false;
        return getRace().equals(character.getRace());
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
