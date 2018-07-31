package designpattern.creational.builder;

public class Computer {
    private String cpu;
    private String mainboard;
    private String fan;

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setMainboard(String mainboard) {
        this.mainboard = mainboard;
    }

    public void setFan(String fan) {
        this.fan = fan;
    }

    public void someExecution(){
    }
}
