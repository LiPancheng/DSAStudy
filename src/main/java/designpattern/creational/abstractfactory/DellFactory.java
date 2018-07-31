package designpattern.creational.abstractfactory;

/**
 * 具体工厂
 */
public class DellFactory extends PCFatory {
    @Override
    public Mouse createMouse() {
        DellMouse dellMouse = new DellMouse();
        return dellMouse;
    }

    @Override
    public Keyboard createKeyboard() {
        DellKeyboard dellKeyboard = new DellKeyboard();
        return dellKeyboard;
    }
}
