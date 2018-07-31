package designpattern.creational.builder;

public class Director {
    /**
     * construct方法的主要职责就是安排建造者的方法执行顺序（因为在实际中不同执行顺序可产生不同结果）
     *
     * 当然也可以省略Director类，将construct方法直接放到抽象建造者类中，抽象建造者类中提供一个默认实现，
     * 可以在具体建造者类中覆盖以安排不同的方法执行顺序
     */
    public Computer construct(Builder builder){
        builder.assembleCPU();
        builder.assembleMainBoard();
        builder.assembleFan();

        return builder.getComputer();
    }
}
