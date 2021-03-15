package ru.an.scg.menu;

public class MenuComponent implements IMenu {
    private IFunctional func;
    private String name;

    public MenuComponent(IFunctional func, String name) {
        this.func = func;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        func.run();
    }
}
