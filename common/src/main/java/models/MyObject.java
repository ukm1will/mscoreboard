package models;

public class MyObject {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "MyObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public MyObject(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
