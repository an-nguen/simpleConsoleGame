package ru.an.scg.models;

public class Actor extends LivingObject {

    public Actor() {}

    @Override
    public void talk(IGameObject target) {

    }

    public Actor(String name, int health) {
        this.health = health;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "health=" + health +
                ", name='" + name + '\'' +
                '}';
    }
}
