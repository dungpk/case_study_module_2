package manager;

import fileIO.ReadToFile;
import fileIO.WriteToFile;
import model.platform.Product;
import model.platform.TypeProduct;
import model.subclass.*;

import java.util.List;

public class ServiceManager {
    static final String CLIENT = "client.txt";
    static final String STORE_MANAGER =  "store_manager.txt";
    static final String CARRIER = "carrier.txt";

    public enum  ClassifyUser {
        ADMIN, HRM, STORE_MANAGER, CARRIER, CLIENT;
    }
    ClassifyUser classifyUser;
    static public ProductManager productManager = new ProductManager();
    static public StoreManager storeManager ;
    static public Client client;
    static public Carrier carrier;
    static public HRM hrm;
    static public Admin admin;


    ReadToFile<Client> readClientToFile = new ReadToFile<>();
    List<Client> clientList = readClientToFile.readToFile(CLIENT);

    static WriteToFile<Client> writeClientToFile = new WriteToFile<>();

    static ReadToFile<Carrier> readCarrierToFile = new ReadToFile<>();
    static WriteToFile<Carrier> writeCarrierToFile = new WriteToFile<>();
    static ReadToFile<StoreManager> readStoreManagerToFile = new ReadToFile<>();
    static WriteToFile<StoreManager> writeStoreManagerToFile = new WriteToFile<>();

    public void displayAllProduct(){
        productManager.display(null);
    }
    public void searchProduct(String name){
        productManager.searchProduct(name);
    }

    public void displayTypeProduct(TypeProduct typeProduct){
        productManager.display(typeProduct);
    }

}
