package ru.an.scg.models;

import java.util.HashSet;
import java.util.Set;

public class Scene {
    private Set<GameObject> gameObjectSet;

    public Scene() {
        gameObjectSet = new HashSet<>();
    }

    public Set<GameObject> getGameObjectSet() {
        return gameObjectSet;
    }

    public void setGameObjectSet(Set<GameObject> gameObjectSet) {
        this.gameObjectSet = gameObjectSet;
    }
}
