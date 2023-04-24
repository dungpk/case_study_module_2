package manager;

import model.platform.TypeProduct;

public class ServiceManager {
    ProductManager productManager = new ProductManager();
    public void displayAllProduct(){
        productManager.display(null);
    }
    public void searProduct(String name){
        productManager.searchProduct(name);
    }

    public void displayTypeProduct(TypeProduct typeProduct){
        productManager.display(typeProduct);
    }

}
