package ru.an.scg;

import ru.an.scg.ui.console_menu.Menu;
import ru.an.scg.ui.console_menu.MenuComponent;
import ru.an.scg.models.Actor;
import ru.an.scg.models.Beast;
import ru.an.scg.models.LivingObjectStates;

public class ConsoleApp implements GameApplication {
    public static Menu buildGameMenu() {
        var player = new Actor("an", 100);
        var beast = new Beast("beast");
        var gameMenu = new Menu();
        gameMenu.addItem(new MenuComponent(() -> {
            player.attack(beast);
            if (beast.getState() == LivingObjectStates.DEAD) {
                beast.setHealth(beast.getMaxHealth());
                player.setExperiencePoints(player.getExperiencePoints());
            }

            beast.attack(player);
            if (player.getState() == LivingObjectStates.DEAD) {
                player.setHealth(player.getMaxHealth());
                player.setExperiencePoints(player.getExperiencePoints() / 2);
            }
            gameMenu.run();
        }, "Attack monster"));
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
        return gameMenu;
    }

    public Menu buildMainMenu() {
        var mainMenu = new Menu("Main menu");
        mainMenu.addItem(new MenuComponent(() -> {

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
