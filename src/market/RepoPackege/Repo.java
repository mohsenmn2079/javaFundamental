package market.RepoPackege;

import market.productsPackage.Product;

public abstract class Repo {
    protected String name;

    public Repo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract public Object getCapacity();
    abstract public Product findProducts(String name);
    abstract public boolean addProductToRepo(Product product);

    abstract public void printProducts();

}
