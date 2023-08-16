package market.productsPackage;

public class Appliance extends Product {
    private int count;
    private String brandName;
    private String guarantee;


    public Appliance(String name, double weight, double price, boolean shipment, int count, String brandName, String guarantee) {
        super(name, weight, price, shipment);
        this.brandName = brandName;
        this.guarantee = guarantee;
        this.count = count;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void printProduct() {
        if (this.getCount() > 0) {
            System.out.println(this.getName());
        }
    }
}
