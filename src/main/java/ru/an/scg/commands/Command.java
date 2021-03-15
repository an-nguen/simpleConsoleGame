package ru.an.scg.commands;

public abstract class Command {
    public abstract boolean execute();
    public abstract void undo();
}
