package market;

import market.productsPackage.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> myItem;
    private double sum;

    public List<Item> getMyItem() {
        return myItem;
    }

    public void setMyItem(List<Item> myItem) {
        this.myItem = myItem;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Cart() {
        this.sum = 0;
        this.myItem = new ArrayList<Item>();
    }

    public boolean addItem(Product product, double weight) {
        this.myItem.add(new Item(product, weight));
        this.sum += weight * (double) product.getPrice();
        return false;
    }
    public boolean addItem(Product product, int count) {
        this.myItem.add(new Item(product, count));
        this.sum += count * (double) product.getPrice();
        return false;
    }
}
