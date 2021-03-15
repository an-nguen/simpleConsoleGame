package ru.an.scg.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu implements IMenu {
    private final List<IMenu> items = new ArrayList<>();
    private String name = "";
    public Menu() {}
    public Menu(String name) {
        this.name = name;
    }

    public void addItem(IMenu item) {
        this.items.add(item);
    }

    public String getMenu() {
        var builder = new StringBuilder();
        builder.append(this.name).append("\n\n");
        int i = 1;
        for (var item : items) {
            builder.append(String.format("%d. ", i)).append(item.getName()).append("\n");
            i++;
        }
        return builder.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void run() {
        System.out.print(getMenu());
        System.out.print("\n>> ");
        while (true) {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String input = reader.readLine();
                var c = Integer.parseInt(input);
                if (c >= 0 && c <= items.size()) {
                    items.get(c - 1).run();
                    break;
                } else {
                    System.out.println("Wrong!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
