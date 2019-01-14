package com.example.lipe.desafio.models;

public class Repository {

    private Owner owner;
    private String name;
    private String description;
    private String stargazers_count;
    private String forks_count;

    public Repository(Owner owner, String name, String description, String stargazers_count, String forks_count) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(String stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getForks_count() {
        return forks_count;
    }

    public void setForks_count(String forks_count) {
        this.forks_count = forks_count;
    }
}
