package market;

import market.productsPackage.Appliance;
import market.productsPackage.Product;

import javax.swing.plaf.IconUIResource;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private Cart userCart;
    private double cash;

    public User(String firstName, String lastName, Cart userCart, double cash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userCart = new Cart();
        this.cash = cash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public boolean addProductToCart(Market market, String nameFood, double weight) {
        for (Product item : market.getRepoFood().getFoodsList()) {
            if (item.getName().equals(nameFood)) {
                double total = item.getPrice() * weight;
                if (item.getWeight() > weight) {
                    if (total<this.getUserCart().getSum()){
                        item.setWeight(item.getWeight() - weight);
                        market.getRepoFood().setInventory(market.getRepoFood().getInventory() - weight);
                        this.userCart.addItem(item, weight);
                        return true;
                    }
                }else {
                    System.out.println("not enough "+ item.getName());
                    return false;
                }
            }
        }
        return false;
    }

    public boolean addProductToCart(Market market, String nameAppliance, int count) {
        for (Product item : market.getRepoAppliances().getApplianceList()) {
            if (item.getName().equals(nameAppliance)) {
                double total = item.getPrice() * count;
                if (((Appliance)item).getCount() > count) {
                    if (total<this.getUserCart().getSum()) {
                        ((Appliance) item).setCount(((Appliance) item).getCount() - count);
                        market.getRepoAppliances().setInventory(market.getRepoAppliances().getInventory() + count);
                        this.userCart.addItem(item, count);
                        return true;
                    }
                }else {
                    System.out.println("not enough "+ item.getName());
                    return false;
                }
            }
        }
        return false;
    }
}
