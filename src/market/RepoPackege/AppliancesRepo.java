package market.RepoPackege;

import market.productsPackage.Appliance;
import market.productsPackage.Product;

import java.util.ArrayList;
import java.util.List;

public class AppliancesRepo extends Repo {
    private List<Product> applianceList;
    private int capacity;
    private int inventory;


    public AppliancesRepo(String name, int capacity, int inventory) {
        super(name);
        this.capacity = capacity;
        this.inventory = inventory;
        this.applianceList = new ArrayList<>();
    }

    @Override
    public Integer getCapacity() {
        return this.capacity - this.inventory;
    }

    @Override
    public Product findProducts(String name) {
        for (Product item : this.applianceList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void setApplianceList(List<Product> applianceList) {
        this.applianceList = applianceList;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public List<Product> getApplianceList() {
        return applianceList;
    }

    public int getInventory() {
        return this.inventory;
    }

    @Override
    public boolean addProductToRepo(Product product) {
        Product item = this.findProducts(product.getName());
        if (item == null) {
            if (this.getCapacity() > ((Appliance) product).getCount()) {
                this.applianceList.add(product);
                this.inventory += (int) ((Appliance) product).getCount();
                return true;
            } else {
                System.out.println("this item can not insert into the repo.");
                return false;
            }
        } else {
            if (this.getCapacity() > ((Appliance) product).getCount()) {
                ((Appliance) item).setCount(((Appliance) product).getCount() + ((Appliance) item).getCount());
                this.inventory += (int) ((Appliance) product).getCount();
                return true;
            } else {
                System.out.println("this item can not insert into the repo.");
                return false;
            }
        }
    }

    @Override
    public void printProducts() {
        if (this.applianceList.isEmpty()){
            System.out.println("no appliance");
        }else {
            for (Product appliance : this.applianceList){
                appliance.printProduct();
            }
        }

    }
}
