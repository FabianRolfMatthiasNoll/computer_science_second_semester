package algorithm_course.VL1.Task5;

public class Object {
    private String name;
    private int value;

    // Constructor
    public Object(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // toString method
    public String toString() {
        return "MyObject [name=" + name + ", value=" + value + "]";
    }
}
