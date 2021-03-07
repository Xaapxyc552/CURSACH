package model.test;

import model.Model;

import java.util.UUID;

public class Topic implements Model {
   private UUID id;
   private String name;

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Предмет: " + name;
    }
}
