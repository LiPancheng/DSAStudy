package designpattern.creational.abstractfactory;

public class DellKeyboard implements Keyboard {
    @Override
    public void input() {
        System.out.println("DellKeyboard input");
    }
}
