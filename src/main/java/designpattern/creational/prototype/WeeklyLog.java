package designpattern.creational.prototype;

import java.io.*;

/**
 * 工作周报类
 */
public class WeeklyLog implements Serializable, Cloneable {
    private Attachment attachment;
    private String name;
    private String date;
    private String content;

    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    /**
     * 使用序列化技术是实现深克隆
     */
    public WeeklyLog deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
        objectOutputStream.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(bis);

        return (WeeklyLog) objectInputStream.readObject();
    }

}
