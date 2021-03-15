package ru.an.scg;

import ru.an.scg.menu.Menu;
import ru.an.scg.menu.MenuComponent;
import ru.an.scg.models.Actor;
import ru.an.scg.models.Beast;

public class ConsoleApp {
    public static Menu buildMainMenu() {
        var player = new Actor("an", 100);
        var beast = new Beast("beast");
        var mainMenu = new Menu();
        mainMenu.addItem(new MenuComponent(() -> {
            player.attack(beast);
            beast.attack(player);
            mainMenu.run();
        }, "Attack monster"));
        mainMenu.addItem(new MenuComponent(() -> {
            player.getCommandHistory().forEach(c -> {
                System.out.println(c.toString());
            });
            mainMenu.run();
        }, "History"));
        mainMenu.addItem(new MenuComponent(() -> System.exit(0), "Exit"));
        return mainMenu;
    }

    public static void main(String[] args) {
        System.out.println("Console game");
        buildMainMenu().run();
    }
}
