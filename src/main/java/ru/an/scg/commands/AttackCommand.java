package ru.an.scg.commands;

import ru.an.scg.models.LivingObject;

import java.util.Random;

public class AttackCommand extends Command {
    private final LivingObject actor;
    private final LivingObject target;
    private int hpValue;

    public AttackCommand(LivingObject actor, LivingObject target) {
        this.actor = actor;
        this.target = target;
    }

    @Override
    public boolean execute() {
        this.hpValue = ((actor.getStrengthPoints() / 2) + actor.getBaseDamage()) * (isMissed(target) ? 0 : 1);
        target.setHealth(target.getHealth() - this.hpValue);
        System.out.printf("%s (%d/%d) attacks %s(%d/%d) -> -%d HP\n",
                actor.getName(), actor.getHealth(), actor.getMaxHealth(),
                target.getName(), target.getHealth(), target.getMaxHealth(),
                hpValue);
        return true;
    }

    private boolean isMissed(LivingObject o) {
        var rng = new Random();
        var randomNumber = rng.nextDouble();
        var missChance = Math.abs(o.getAgilityPoints() * 0.005);
        return randomNumber <= missChance;
    }

    @Override
    public void undo() {
        target.setHealth(target.getHealth() + this.hpValue);
    }

    @Override
    public String toString() {
        return "AttackCommand{" +
                "actor=" + actor +
                ", target=" + target +
                ", hpValue=" + hpValue +
                '}';
    }
}
