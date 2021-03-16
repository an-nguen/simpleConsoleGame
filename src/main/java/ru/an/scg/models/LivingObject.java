package ru.an.scg.models;

import ru.an.scg.commands.AttackCommand;
import ru.an.scg.commands.Command;

import java.util.Stack;

public abstract class LivingObject implements GameObject {
    protected double health;
    protected String name;
    protected final Stack<Command> commandHistory = new Stack<>();
    protected double expPointsAfterDead = 1;
    protected LivingObjectStates state;
    // damage = BASE_DAMAGE + (strengthPoints / 2)
    protected int strengthPoints = 10;
    // Miss chance
    protected int agilityPoints = 10;
    // hp = BASE_HEALTH_POINTS * endurancePoints
    protected int endurancePoints = 10;
    private int baseHP = 10;
    private int baseDamage = 10;


    public LivingObject() {
        this.health = baseHP * endurancePoints;
    }

    public LivingObject(int health) {
        this.health = health;
    }

    public void attack(LivingObject target) {
        var command = new AttackCommand(this, target);
        commandHistory.push(command);
        command.execute();
        target.checkState();
        target.onAfterAttack();
    }

    public abstract void onAfterAttack();

    public LivingObjectStates getState() {
        return state;
    }

    public void checkState() {
        state = (health < 0) ? LivingObjectStates.DEAD : LivingObjectStates.LIVE;
    }

    public abstract void talk(GameObject target);

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public LivingObject setStrength(int v) {
        this.strengthPoints = v;
        return this;
    }

    public LivingObject setAgility(int v) {
        this.agilityPoints = v;
        return this;
    }

    public LivingObject setEndurance(int v) {
        this.endurancePoints = v;
        return this;
    }

    public int getStrengthPoints() {
        return strengthPoints;
    }

    public int getAgilityPoints() {
        return agilityPoints;
    }

    public int getEndurancePoints() {
        return endurancePoints;
    }

    public double getExpPointsAfterDead() {
        return expPointsAfterDead;
    }

    public void setExpPointsAfterDead(double expPointsAfterDead) {
        this.expPointsAfterDead = expPointsAfterDead;
    }

    public Stack<Command> getCommandHistory() {
        return commandHistory;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(double v) {
        this.health = v;
    }

    @Override
    public int getMaxHealth() {
        return this.baseHP * this.endurancePoints;
    }
}
