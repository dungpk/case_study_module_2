package model.platform;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String size;
    private int price;
    private int quantity;
    private TypeProduct typeProduct;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public Product(String name, String size, int price, int quantity, TypeProduct typeProduct) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.typeProduct = typeProduct;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product: " +
                "name: '" + name + "\n" +
                "size: " + size + "\n"+
                "price: " + price +"\n"+
                "quantity: " + quantity +"\n" +
                "typeProduct: " + typeProduct +"\n";
    }
}
