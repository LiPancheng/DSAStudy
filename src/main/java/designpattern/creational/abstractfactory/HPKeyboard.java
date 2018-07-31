package designpattern.creational.abstractfactory;

public class HPKeyboard implements Keyboard {
    @Override
    public void input() {
        System.out.println("HPKeyboard input");
    }
}
