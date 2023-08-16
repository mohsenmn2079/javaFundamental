package market.productsPackage;

public class Food extends Product {
    private String expirationDate;

    public String getExpirationDate() {
        return expirationDate;
    }

    public Food(String name, double weight, double price, boolean shipment, String expirationDate) {
        super(name, weight, price, shipment);
        this.expirationDate = expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public void printProduct() {
        if (this.getWeight()>0){
            System.out.println(this.getName());
        }

    }
}
