package ru.an.scg.commands;

public abstract class Command {
    public abstract void execute();
    public abstract void undo();
}
