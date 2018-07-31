package designpattern.creational.abstractfactory;

public class DellMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("DellMouse click");
    }
}
