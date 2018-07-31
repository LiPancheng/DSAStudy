package designpattern.creational.singleton;

public class StaticInnerClass {

    private StaticInnerClass(){}

    private static class ClassHolder{
        private static final StaticInnerClass INSTANCE = new StaticInnerClass();
    }

    public StaticInnerClass getInstance(){
        return ClassHolder.INSTANCE;
    }
}
