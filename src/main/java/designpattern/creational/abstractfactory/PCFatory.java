package designpattern.creational.abstractfactory;

/**
 * 抽象工厂
 * 可以使用抽象类或者接口
 */
public abstract class PCFatory {
    public abstract Mouse createMouse();
    public abstract Keyboard createKeyboard();
}
