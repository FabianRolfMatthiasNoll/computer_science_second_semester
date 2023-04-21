package algorithm_course.VL1.Task4;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public List<Product> getProductsByShelfNumber(int shelfNumber) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getShelfNumber() == shelfNumber) {
                result.add(product);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}

