package model.subclass;

import manager.ProductManager;
import model.platform.Product;
import model.platform.TypeUser;
import model.platform.User;
import model.revenue.RevenueManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client extends User implements Serializable {
    private List<Product> Cart = new ArrayList<>();
    static ProductManager productClient = new ProductManager();

    public Client(String name, String id, String account, String password) {
        super(name, id, new TypeUser(TypeUser.ClassifyUser.CLIENT, TypeUser.Sex.MALE), account, password);
    }

    public List<Product> getCart() {
        return Cart;
    }

    public void displayCart(){
        if(Cart.isEmpty()){
            System.out.println("Giỏ hàng của bạn trống");
        }else{
            System.out.println("Giỏ hàng của bạn gồm:");
            System.out.println(Cart);
        }
    }

    public void setCart(List<Product> cart) {
        Cart = cart;
    }
    public void searchProduct(String name) {
        productClient.searchProduct(name);
    }
    public void displayProduct(){
        productClient.display(null);
    }
    public void addToCart(String name,int quantity){
        if(productClient.getIndexByName(name) == -1){
            System.out.println("Không tồn tại sản phẩm: "+ name);
        }else{
            for (Product product : productClient.productList) {
                if(product.getName().equals(name)){
                    if(quantity>product.getQuantity()){
                        System.out.println("Số lượng sản phẩm không đủ "+ quantity);
                    }else{
                        Product pr = product;
                        pr.setQuantity(quantity);
                        Cart.add(pr);
                        System.out.println("Sản phẩm được thêm thành công");
                    }
                    break;
                }
            }
        }

    }

    @Override
    public String toString() {
        return "Client: "+super.toString();
    }
}
