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
    static public StoreManager storeManager ;
    static public Client client;
    static public Carrier carrier;
    static public HRM hrm;
    static public Admin admin;
    static public ProductManager productManager = new ProductManager();
    static List<StoreManager> listStoreManager = new ArrayList<>();
    static List<Client> listClient = new ArrayList<>();
    static List<Carrier> listCarrier = new ArrayList<>();

    static List<HRM> listHRM = new ArrayList<>();
    static List<Admin> listAdmin = new ArrayList<>();

    public ServiceManager() {

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
    private void readCarrierList() {
        listCarrier = readCarrierToFile.readToFile(CARRIER);
    }
    private void writeCarrierList( ){
        writeCarrierToFile.writeToFile(CARRIER,listCarrier);
    }
    private void readClientList() {
        listClient = readClientToFile.readToFile(CLIENT);
    }
    private void writeClientList( ){
        writeClientToFile.writeToFile(CLIENT,listClient);
    }
    private void readStoreManagerList() {
        listStoreManager = readStoreManagerToFile.readToFile(STORE_MANAGER);
    }
    private void writeStoreManagerList( ){
        writeStoreManagerToFile.writeToFile(STORE_MANAGER,listStoreManager);
    }
    private void readHRMList() {
        listHRM = readHRMToFile.readToFile(HRM);
    }
    private void writeHRMList(){
        writeHRMToFile.writeToFile(HRM,listHRM);
    }
    private void readAdminList() {
        listAdmin = readAdminToFile.readToFile(ADMIN);
    }
    private void writeAdminList(){
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
