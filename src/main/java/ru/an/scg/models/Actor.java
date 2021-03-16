package ru.an.scg.models;

public class Actor extends LivingObject {
    private double experiencePoints;
    public Actor() {}

    @Override
    public void onAfterAttack() {

    }

    @Override
    public void talk(GameObject target) {

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

    public void setExperiencePoints(double ep) {
        this.experiencePoints = ep;
    }

    public double getExperiencePoints() {
        return experiencePoints;
    }
}
