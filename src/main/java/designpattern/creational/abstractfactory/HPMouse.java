package designpattern.creational.abstractfactory;

/**
 * 具体产品
 */
public class HPMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("HPMouse click");
    }
}
