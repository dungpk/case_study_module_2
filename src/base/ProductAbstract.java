package base;


import model.platform.Product;
import model.platform.TypeProduct;

public abstract class ProductAbstract {
    public abstract void display(TypeProduct type);
    public abstract void insertProduct(Product product);
    public abstract void removeProduct(String name);
    public abstract void updatePriceProduct(String name, int price);
    public abstract void updateQuantityProduct(String name, int quantity);
}
