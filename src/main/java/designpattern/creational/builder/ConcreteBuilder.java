package designpattern.creational.builder;

public class ConcreteBuilder extends Builder {
    private Computer computer;

    @Override
    public void assembleCPU() {
        computer.setCpu("cpu");
        System.out.println("组装CPU");
    }

    @Override
    public void assembleMainBoard() {
        computer.setMainboard("mainBoard");
        System.out.println("组装主板");
    }

    @Override
    public void assembleFan() {
        computer.setFan("fan");
        System.out.println("组装风扇");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
