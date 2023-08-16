package market.productsPackage;

public abstract class Product {
    protected String name;
    protected double weight;
    protected double price;

    protected boolean shipment;

    public Product(String name, double weight, double price, boolean shipment) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.shipment = shipment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setShipment(boolean shipment) {
        this.shipment = shipment;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public boolean getShipment() {
        return shipment;
    }
    public abstract void printProduct();
}
