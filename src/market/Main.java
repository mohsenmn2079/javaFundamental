package market;

import market.RepoPackege.AppliancesRepo;
import market.RepoPackege.FoodRepo;
import market.productsPackage.Appliance;
import market.productsPackage.Food;

public class Main {
    public static void main(String[] args) {
        FoodRepo foodRepo = new FoodRepo("food repository", 10000, 0);
        AppliancesRepo applianceRepo = new AppliancesRepo("appliance repository", 1000, 0);
        Market market = new Market("Hyper Market", foodRepo, applianceRepo);


        market.addProductToMarket(new Food("banana", 1000, 3, false, "2023/1"));
        market.addProductToMarket(new Food("orange", 500, 2, false, "2023/1"));
        market.addProductToMarket(new Food("cake", 100, 3, false, "2023/1"));
        market.addProductToMarket(new Food("chocolate", 300, 6, false, "2023/4"));
        market.addProductToMarket(new Food("apple", 50, 3, false, "2023/2"));
        market.addProductToMarket(new Food("sugar", 250, 2, false, "2023/4"));

        market.addProductToMarket(new Appliance("TV", 2, 1500, true, 50, "LG", "1 year"));
        market.addProductToMarket(new Appliance("Laptop", 1.3, 1600, true, 50, "Apple", "1 year"));
        market.addProductToMarket(new Appliance("Mobile", 0.2, 1300, true, 50, "Samsung", ""));
        market.addProductToMarket(new Appliance("Headphone", 0.1, 1500, true, 50, "Apple", "1 year"));

        market.printAvailableProduct();

        User mohsen = new User("mohsen" , "mostaghisi" , null,3000);
        mohsen.addProductToCart(market,"banana",1.5);

    }
}