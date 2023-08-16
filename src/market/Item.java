package market;

import market.productsPackage.Product;

public class Item {
    private Product product;
    private int count;
    private double weight;

    public Item(Product product, double weight) {
        this.product = product;
        this.weight = weight;
    }

    public Item(Product product, int count) {
        this.product = product;
        this.count = count;
    }
}
