package manager;

import fileIO.ReadToFile;
import fileIO.WriteToFile;
import model.platform.TypeProduct;
import model.subclass.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager implements Serializable {
    static final String CLIENT = "client.txt";
    static final String STORE_MANAGER =  "store_manager.txt";
    static final String CARRIER = "carrier.txt";
    static final String HRM = "hrm.txt";
    static final String ADMIN = "admin.txt";

    public enum  ClassifyUser {
        ADMIN, HRM, STORE_MANAGER, CARRIER, CLIENT,EMPTY
    }
    public static String accountUser;
    public static String nameUser;


    public static ClassifyUser classifyUser;
     public StoreManager storeManager ;
     public Client client;
     public Carrier carrier;
     public HRM hrm;
     public Admin admin;
     public static ProductManager productManagerInService = new ProductManager();
    public static List<StoreManager> listStoreManagerInService = new ArrayList<>();
    public static List<Client> listClientInService = new ArrayList<>();
    public static List<Carrier> listCarrierInService = new ArrayList<>();

    public static List<HRM> listHRMInService = new ArrayList<>();
    public static List<Admin> listAdminInService = new ArrayList<>();

    public ServiceManager() {
        readAllUserList();
    }
    static ReadToFile<HRM> readHRMToFileInService = new ReadToFile<>();
    static WriteToFile<HRM> writeHRMToFileInService = new WriteToFile<>();
    static ReadToFile<Admin> readAdminToFileInService = new ReadToFile<>();
    static WriteToFile<Admin> writeAdminToFileInService = new WriteToFile<>();
    static ReadToFile<Client> readClientToFileInService = new ReadToFile<>();
    static WriteToFile<Client> writeClientToFileInService = new WriteToFile<>();
    static ReadToFile<Carrier> readCarrierToFileInService = new ReadToFile<>();
    static WriteToFile<Carrier> writeCarrierToFileInService = new WriteToFile<>();
    static ReadToFile<StoreManager> readStoreManagerToFileInService = new ReadToFile<>();
    static WriteToFile<StoreManager> writeStoreManagerToFileInService = new WriteToFile<>();

    public void displayAllProduct(){
        productManagerInService.display(null);
    }
    public void searchProduct(String name){
        productManagerInService.searchProduct(name);
    }
    public void displayTypeProduct(TypeProduct typeProduct){
        productManagerInService.display(typeProduct);
    }
    public void readCarrierList() {
        listCarrierInService = readCarrierToFileInService.readToFile(CARRIER);
    }
    public void writeCarrierList( ){
        writeCarrierToFileInService.writeToFile(CARRIER,listCarrierInService);
    }
    public void readClientList() {
        listClientInService = readClientToFileInService.readToFile(CLIENT);
    }
    public void writeClientList(){
        writeClientToFileInService.writeToFile(CLIENT,listClientInService);
    }
    public void addNewClient(Client client){
        readClientList();
        listClientInService.add(client);
        writeClientList();
    }


    public void readStoreManagerList() {
        listStoreManagerInService = readStoreManagerToFileInService.readToFile(STORE_MANAGER);
    }
    public void writeStoreManagerList( ){
        writeStoreManagerToFileInService.writeToFile(STORE_MANAGER,listStoreManagerInService);
    }
    public void readHRMList() {
        listHRMInService = readHRMToFileInService.readToFile(HRM);
    }
    public void writeHRMList(){
        writeHRMToFileInService.writeToFile(HRM,listHRMInService);
    }
    public void readAdminList() {
        listAdminInService = readAdminToFileInService.readToFile(ADMIN);
    }
    public void writeAdminList(){
        writeAdminToFileInService.writeToFile(ADMIN,listAdminInService);
    }
    private void readAllUserList(){
        readHRMList();
        readStoreManagerList();
        readClientList();
        readCarrierList();
        readAdminList();
    }

    public ClassifyUser searchInUserList(String account){
        for (Admin admin : listAdminInService) {
            if(account.equals(admin.getAccount())){
                return ClassifyUser.ADMIN;
            }
        }
        for (HRM hrm : listHRMInService) {
            if(account.equals(hrm.getAccount())){
                return ClassifyUser.HRM;
            }
        }
        for (StoreManager storeManager : listStoreManagerInService) {
            if(account.equals(storeManager.getAccount())){
                return ClassifyUser.STORE_MANAGER;
            }
        }

        for (Carrier carrier : listCarrierInService) {
            if(account.equals(carrier.getAccount())){
                return ClassifyUser.CARRIER;
            }
        }
        for (Client client : listClientInService) {
            if(account.equals(client.getAccount())){
                return ClassifyUser.CLIENT;
            }
        }
        return  ClassifyUser.EMPTY;

    }

    public ClassifyUser validateTypeAccountWhenLogin(String account,String password){
        for (Admin admin : listAdminInService) {
            if(account.equals(admin.getAccount())){
                if(admin.getPassword().equals(password)){
                    ServiceManager.classifyUser = ServiceManager.ClassifyUser.ADMIN;
                    ServiceManager.accountUser = account;
                    ServiceManager.nameUser = admin.getName();
                    return ClassifyUser.ADMIN;
                }else{
                    return null;
                }

            }
        }
        for (HRM hrm : listHRMInService) {
            if(account.equals(hrm.getAccount())){
                if(hrm.getPassword().equals(password)){
                    ServiceManager.classifyUser = ServiceManager.ClassifyUser.HRM;
                    ServiceManager.accountUser = account;
                    ServiceManager.nameUser = hrm.getName();
                    return ClassifyUser.HRM;
                }else{
                    return null;
                }
            }
        }
        for (StoreManager storeManager : listStoreManagerInService) {
            if(account.equals(storeManager.getAccount())){
                if(storeManager.getPassword().equals(password)){
                    ServiceManager.classifyUser = ClassifyUser.STORE_MANAGER;
                    ServiceManager.accountUser = account;
                    ServiceManager.nameUser = storeManager.getName();
                    return ClassifyUser.STORE_MANAGER;
                }else{
                    return null;
                }
            }
        }

        for (Carrier carrier : listCarrierInService) {
            if(account.equals(carrier.getAccount())){
                if(carrier.getPassword().equals(password)){
                    ServiceManager.classifyUser = ClassifyUser.CARRIER;
                    ServiceManager.accountUser = account;
                    ServiceManager.nameUser = carrier.getName();
                    return ClassifyUser.CARRIER;
                }else{
                    return null;
                }
            }
        }
        for (Client client : listClientInService) {
            if(account.equals(client.getAccount())){
                if(client.getPassword().equals(password)){
                    ServiceManager.classifyUser = ClassifyUser.CLIENT;
                    ServiceManager.accountUser = account;
                    ServiceManager.nameUser = client.getName();
                    return ClassifyUser.CLIENT;
                }else{
                    return null;
                }
            }
        }
        return  ClassifyUser.EMPTY;
    }
}
