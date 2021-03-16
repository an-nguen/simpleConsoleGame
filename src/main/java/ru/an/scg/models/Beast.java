package ru.an.scg.models;

public class Beast extends LivingObject{
    @Override
    public void onAfterAttack() {

    }

    @Override
    public void talk(GameObject target) {

    }

    public Beast(String name) {
        this.name = name;
    }
}
