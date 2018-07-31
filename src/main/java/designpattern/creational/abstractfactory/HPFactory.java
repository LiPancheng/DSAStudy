package designpattern.creational.abstractfactory;

/**
 * 具体工厂
 */
public class HPFactory extends PCFatory {
    @Override
    public Mouse createMouse() {
        HPMouse hpMouse = new HPMouse();
        //other code
        return hpMouse;
    }

    @Override
    public Keyboard createKeyboard() {
        return null;
    }
}
