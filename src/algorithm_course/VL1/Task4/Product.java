package algorithm_course.VL1.Task4;

public class Product {
    private int id;
    private String name;
    private double netPrice;
    private int shelfNumber;

    public Product(int id, String name, double netPrice, int shelfNumber) {
        this.id = id;
        this.name = name;
        this.netPrice = netPrice;
        this.shelfNumber = shelfNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }
}
