package ru.an.scg;

import ru.an.scg.models.*;
import ru.an.scg.ui.console_menu.Menu;
import ru.an.scg.ui.console_menu.MenuComponent;

import java.util.HashSet;

public class ConsoleApp implements GameApplication {
    private Scene mainScene;
    private Actor player;

    public Menu buildGameMenu() {
        var gameMenu = new Menu();
        gameMenu.addItem(new MenuComponent(() -> {
            var selectTargetMenu = new Menu();
            for (var target : mainScene.getGameObjectSet()) {
                selectTargetMenu.addItem(new MenuComponent(() -> {
                    player.attack((LivingObject) target);
                    if (((LivingObject) target).getState() == LivingObjectStates.DEAD) {
                        player.setExperiencePoints(player.getExperiencePoints() + ((LivingObject) target).getExpPointsAfterDead());
                        this.mainScene.getGameObjectSet().remove(target);
                        gameMenu.run();
                    } else {
                        ((LivingObject) target).attack(player);

                        if (player.getState() == LivingObjectStates.DEAD) {
                            ((LivingObject) target).setExpPointsAfterDead(((LivingObject) target).getExpPointsAfterDead() + player.getExperiencePoints());
                            System.out.println("You dead!");
                            buildMainMenu().run();
                        } else {
                            gameMenu.run();
                        }
                    }
                }, target.getName()));
            }
            selectTargetMenu.addItem(new MenuComponent(gameMenu::run, "Back"));
            selectTargetMenu.run();
        }, "Attack"));
        gameMenu.addItem(new MenuComponent(() -> {
            player.getCommandHistory().forEach(c -> {
                System.out.println(c.toString());
            });
            gameMenu.run();
        }, "History"));
        gameMenu.addItem(new MenuComponent(() -> {
            System.out.println(player.toString());
            gameMenu.run();
        }, "Player info"));
        gameMenu.addItem(new MenuComponent(() -> {
            buildMainMenu().run();
        }, "Back"));
        return gameMenu;
    }

    private HashSet<GameObject> getGameObjects() {
        var objs = new HashSet<GameObject>();
        objs.add(new Beast("test_beast1"));
        objs.add(new Beast("test_beast2"));
        objs.add(new Beast("test_beast3"));
        return objs;
    }

    public Menu buildMainMenu() {
        var mainMenu = new Menu("Main menu");
        mainMenu.addItem(new MenuComponent(() -> {
            mainScene = new Scene();
            player = new Actor("an", 100);
            mainScene.getGameObjectSet().add(player);
            mainScene.getGameObjectSet().addAll(getGameObjects());
            buildGameMenu().run();
        }, "Start game"));
        mainMenu.addItem(new MenuComponent(() -> {

        }, "Save"));
        mainMenu.addItem(new MenuComponent(() -> {

        }, "Load"));
        mainMenu.addItem(new MenuComponent(this::stop, "Exit"));
        return mainMenu;
    }

    public static void main(String[] args) {
        var app = new ConsoleApp();
        app.start();
    }

    @Override
    public void start() {
        buildMainMenu().run();
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public void stop() {

    }
}
