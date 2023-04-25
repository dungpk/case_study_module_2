package manager;

import fileIO.ReadToFile;
import fileIO.WriteToFile;
import model.platform.Product;
import model.platform.TypeProduct;
import model.revenue.RevenueManager;
import model.subclass.*;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    static final String CLIENT = "client.txt";
    static final String STORE_MANAGER =  "store_manager.txt";
    static final String CARRIER = "carrier.txt";
    static final String HRM = "hrm.txt";
    static final String ADMIN = "admin.txt";

    public enum  ClassifyUser {
        ADMIN, HRM, STORE_MANAGER, CARRIER, CLIENT;
    }
    public static String account;

    ClassifyUser classifyUser;
     public StoreManager storeManager ;
     public Client client;
     public Carrier carrier;
     public HRM hrm;
     public Admin admin;
     public static ProductManager productManager = new ProductManager();
    public static List<StoreManager> listStoreManager = new ArrayList<>();
    public static List<Client> listClient = new ArrayList<>();
    public static List<Carrier> listCarrier = new ArrayList<>();

    public static List<HRM> listHRM = new ArrayList<>();
    public static List<Admin> listAdmin = new ArrayList<>();

    public ServiceManager() {
        readAllUserList();
    }
    static ReadToFile<HRM> readHRMToFile = new ReadToFile<>();
    static WriteToFile<HRM> writeHRMToFile = new WriteToFile<>();
    static ReadToFile<Admin> readAdminToFile = new ReadToFile<>();
    static WriteToFile<Admin> writeAdminToFile = new WriteToFile<>();
    static ReadToFile<Client> readClientToFile = new ReadToFile<>();
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
    public void readCarrierList() {
        listCarrier = readCarrierToFile.readToFile(CARRIER);
    }
    public void writeCarrierList( ){
        writeCarrierToFile.writeToFile(CARRIER,listCarrier);
    }
    public void readClientList() {
        listClient = readClientToFile.readToFile(CLIENT);
    }
    public void writeClientList( ){
        writeClientToFile.writeToFile(CLIENT,listClient);
    }
    public void readStoreManagerList() {
        listStoreManager = readStoreManagerToFile.readToFile(STORE_MANAGER);
    }
    public void writeStoreManagerList( ){
        writeStoreManagerToFile.writeToFile(STORE_MANAGER,listStoreManager);
    }
    public void readHRMList() {
        listHRM = readHRMToFile.readToFile(HRM);
    }
    public void writeHRMList(){
        readAdminList();
        writeHRMToFile.writeToFile(HRM,listHRM);
        readAdminList();
    }
    public void readAdminList() {
        listAdmin = readAdminToFile.readToFile(ADMIN);
    }
    public void writeAdminList(){
        writeAdminToFile.writeToFile(ADMIN,listAdmin);
    }
    private void readAllUserList(){
        readHRMList();
        readStoreManagerList();
        readClientList();
        readCarrierList();
        readAdminList();
    }
}
