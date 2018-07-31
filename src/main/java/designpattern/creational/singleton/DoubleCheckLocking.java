package designpattern.creational.singleton;

public class DoubleCheckLocking {
    private volatile static DoubleCheckLocking instance;

    private DoubleCheckLocking(){}

    public static DoubleCheckLocking getInstance() {
        if (instance == null){
            synchronized (DoubleCheckLocking.class){
                if (instance == null){
                    instance = new DoubleCheckLocking();
                }
            }
        }
        return instance;
    }
}
