package designpattern.creational.builder;

public abstract class Builder {
    public abstract void assembleCPU();

    public abstract void assembleMainBoard();

    public abstract void assembleFan();

    public abstract Computer getComputer();
}
