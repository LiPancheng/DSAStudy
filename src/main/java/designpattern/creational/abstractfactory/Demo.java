package designpattern.creational.abstractfactory;

public class Demo {
    public static void main(String[] args) {
        PCFatory pcFatory = new HPFactory();//实际开发中，可通过XML配置想传入的具体工厂类名，然后利用工具类解析并反射创建一个对象
        Mouse mouse = pcFatory.createMouse();
        mouse.click();
    }
}
