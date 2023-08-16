package market;

import market.RepoPackege.AppliancesRepo;
import market.RepoPackege.FoodRepo;
import market.productsPackage.Product;

public class Market {
    private String name;
    private FoodRepo repoFood;
    private AppliancesRepo repoAppliances;
    public Market(String name, FoodRepo repoFood, AppliancesRepo repoAppliances) {
        this.name = name;
        this.repoFood = repoFood;
        this.repoAppliances = repoAppliances;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodRepo getRepoFood() {
        return this.repoFood;
    }

    public void setRepoFood(FoodRepo repoFood) {
        this.repoFood = repoFood;
    }

    public AppliancesRepo getRepoAppliances() {
        return repoAppliances;
    }

    public void setRepoAppliances(AppliancesRepo repoAppliances) {
        this.repoAppliances = repoAppliances;
    }
    public boolean addProductToMarket(Product product){
        if (product.getClass().toString().equals("class productsPackage.Food")){
            return this.repoFood.addProductToRepo(product);
        }
        return this.repoAppliances.addProductToRepo(product);
    }
    public void printAvailableProduct(){
        System.out.println("foods list:");
        this.repoFood.printProducts();
        System.out.println();
        System.out.println("appliances list:");
        this.repoAppliances.printProducts();
    }


}
