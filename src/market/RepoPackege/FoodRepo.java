package market.RepoPackege;

import market.productsPackage.Product;

import java.util.ArrayList;
import java.util.List;

public class FoodRepo extends Repo {
    private List<Product> foodsList;
    private double capacity;
    private double inventory;

    public FoodRepo(String name,double capacity, double inventory) {
        super(name);
        this.capacity = capacity;
        this.inventory = inventory;
        this.foodsList = new ArrayList<>();
    }

    @Override
    public Double getCapacity() {
        return this.capacity - this.inventory;
    }

    @Override
    public Product findProducts(String name) {
        for (Product item : this.foodsList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public double getInventory() {
        return this.inventory;
    }

    public void setFoodsList(List<Product> foodsList) {
        this.foodsList = foodsList;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    public List<Product> getFoodsList() {
        return this.foodsList;
    }

    @Override
    public boolean addProductToRepo(Product product) {
        Product item = this.findProducts(product.getName());
        if (item == null) {
            if (this.getCapacity() > product.getWeight()) {
                this.foodsList.add(product);
                this.inventory += product.getWeight();
                return true;
            }
            else {
                System.out.println("this item can not insert into the repo.");
                return false;
            }
        }
        else{
            if (this.getInventory() > product.getWeight()){
                item.setWeight(item.getWeight() + product.getWeight());
                return true;
            }
            else {
                System.out.println("this item can not insert into the repo.");
                return false;
            }
        }
    }

    @Override
    public void printProducts() {
        if (this.foodsList.isEmpty()){
            System.out.println("no food");
        }else {
            for (Product appliance : this.foodsList){
                appliance.printProduct();
            }
        }
    }
}
