package ru.job4j.generic;

public class Role extends User{

    private final String proffesion;

    public Role(String id, String name, String proffesion) {
        super(id, name);
        this.proffesion = proffesion;
    }

    public String getProffesion() {
        return proffesion;
    }
}
