package ru.an.scg.ui.console_menu;

public class MenuComponent implements MenuComposite {
    private Runnable func;
    private String name;

    public MenuComponent(Runnable func, String name) {
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
