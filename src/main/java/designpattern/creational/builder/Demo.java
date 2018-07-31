package designpattern.creational.builder;

public class Demo {
    public static void main(String[] args) {
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        Computer computer = new Director().construct(concreteBuilder);
        computer.someExecution();
    }
}
