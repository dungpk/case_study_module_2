package model.subclass;

import base.Payroll;
import fileIO.ReadToFile;
import fileIO.WriteToFile;
import model.platform.Product;
import model.platform.TypeUser;
import model.platform.User;
import model.revenue.Revenue;
import model.revenue.RevenueManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class HRM extends User implements Serializable, Payroll {

    private double  basicSalary = 500000;
    private double workingDay = 30;
    private double coefficient = 0.4 ;

    final int CLIENT_USER = 1;
    final int CARRIER_USER = 2;
    final int STORE_MANAGER_USER = 3;


    static final String CLIENT = "client.txt";
    static final String STORE_MANAGER =  "store_manager.txt";
    static final String CARRIER = "carrier.txt";

    public RevenueManager HRMRevenue = new RevenueManager();
    static List<StoreManager> listStoreManager = new ArrayList<>();
    static List<Client> listClient = new ArrayList<>();
    static List<Carrier> listCarrier = new ArrayList<>();

    static ReadToFile<Client> readClientToFile = new ReadToFile<>();
    static WriteToFile<Client> writeClientToFile = new WriteToFile<>();

    static ReadToFile<Carrier> readCarrierToFile = new ReadToFile<>();
    static WriteToFile<Carrier> writeCarrierToFile = new WriteToFile<>();
    static ReadToFile<StoreManager> readStoreManagerToFile = new ReadToFile<>();
    static WriteToFile<StoreManager> writeStoreManagerToFile = new WriteToFile<>();

    public HRM(String name, String id, String account, String password) {
        super(name, id,new TypeUser(TypeUser.ClassifyUser.HRM, TypeUser.Sex.FEMALE), account, password);
        readClientList();
        readCarrierList();
        readStoreManagerList();
    }

    @Override
    public double payroll() {
        return basicSalary*workingDay+coefficient*HRMRevenue.totalRevenue();
    }

    public void hrmDisplayRevenue(){
        HRMRevenue.displayRevenue();
    }
     public void hrmClearRevenue(){
        HRMRevenue.clearRevenue();
     }

     public void hrmUpdateDayRevenue(int day,double revenue){
        HRMRevenue.updateRevenue(day,revenue);
     }
     public void hrmAddNewRevenue(double revenue){
         Revenue revenueAdd = new Revenue(revenue,0);
        HRMRevenue.insertRevenue(revenueAdd);
     }

     public double totalRevenue(){
        return HRMRevenue.totalRevenue();
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

    public void displayCarrierList(){
        readCarrierList();
        System.out.println(listCarrier);
    }

    public void displayClientList(){
        readClientList();
        System.out.println(listClient);
    }
    public void displayStoreManagerList(){
        readStoreManagerList();
        System.out.println(listStoreManager);
    }
    public void displayUserList(){
        readClientList();
        readCarrierList();
        readStoreManagerList();
        System.out.println(listClient);
        System.out.println("=================================");
        System.out.println(listStoreManager);
        System.out.println("=================================");
        System.out.println(listCarrier);
    }


    public void userAccountCreation(int value,User user){
        switch(value){
            case CLIENT_USER:
                readClientList();
                Client client = new Client(user.getName(), user.getId(), user.getAccount(), user.getPassword());
                listClient.add(client);
                writeClientList();
                break;
            case STORE_MANAGER_USER:
                readStoreManagerList();
                StoreManager storeManager =
                        new StoreManager(user.getName(), user.getId(), user.getAccount(), user.getPassword());
                listStoreManager.add(storeManager);
                writeStoreManagerList();
                break;
            case CARRIER_USER:
                readCarrierList();
                Carrier carrier =
                        new Carrier(user.getName(), user.getId(), user.getAccount(), user.getPassword());
                listCarrier.add(carrier);
                writeCarrierList();
                break;

            default:
                System.out.println("Không thể khởi tạo đối tượng");

        }
    }

    public void hrmRemoveUser(String account,int typeUser){
        int index;
        switch (typeUser){
            case CLIENT_USER:
                index = getIndexByNameClient(account);
                if (index >= 0) {
                    listClient.remove(index);
                    writeClientList();
                    System.out.println("Xoá tài khoản thành công");
                }else{
                    System.out.println("Không tồn tại account "+account + " trong client list");
                }
                break;
            case CARRIER_USER:
                index = getIndexByNameCarrier(account);
                if (index >= 0) {
                    listCarrier.remove(index);
                    writeCarrierList();
                    System.out.println("Xoá tài khoản thành công");
                }else{
                    System.out.println("Không tồn tại account "+account + " trong carrier list");
                }
                break;
            case STORE_MANAGER_USER:
                index = getIndexByNameStoreManager(account);
                if (index >= 0) {
                    listStoreManager.remove(index);
                    writeStoreManagerList();
                    System.out.println("Xoá tài khoản thành công");
                }else{
                    System.out.println("Không tồn tại account "+account + " trong store manager list");
                }
                break;
            default:
                System.out.println("Không xóa account:"+account);
                break;
        }

    }

    public int getIndexByNameCarrier(String accountCarrier) {
        int index = -1;
        readClientList();
        for (Carrier carrier : listCarrier ) {
            if (carrier.getAccount().equals(accountCarrier)) {
                return listCarrier.indexOf(carrier);
            }
        }
        return index;
    }
    public int getIndexByNameClient(String accountClient) {
        int index = -1;
        readClientList();
        for (Client client : listClient ) {
            if (client.getAccount().equals(accountClient))
                return listClient.indexOf(client);
            }
        return index;
    }
    public int getIndexByNameStoreManager(String accountStoreManager) {
        int index = -1;
        readStoreManagerList();
        for (StoreManager storeManager : listStoreManager ) {
            if (storeManager.getAccount().equals(accountStoreManager)) {
                return listStoreManager.indexOf(storeManager);
            }
        }
        return index;
    }
    public void updateClient(String account, Client clientUpdate) {
        int index = getIndexByNameClient(account);
        if (index < 0) {
            System.out.println("Client not found!");
        } else {
            for (Client client : listClient) {
                if (client.getAccount().equals(account)) {
                    client.setPassword(clientUpdate.getPassword());
                    client.setName(clientUpdate.getName());
                    System.out.println("Cập nhật client thành công");
                    break;

                }
            }
            writeClientList();
        }


    }

    public void updateCarrier(String account, Carrier carrierUpdate) {
        int index = getIndexByNameCarrier(account);
        if (index < 0) {
            System.out.println("Carrier not found!");
        } else {
            for (Carrier carrier : listCarrier) {
                if (carrier.getAccount().equals(account)) {
                    carrier.setPassword(carrierUpdate.getPassword());
                    carrier.setName(carrierUpdate.getName());
                    carrier.setCoefficient(carrierUpdate.getCoefficient());
                    carrier.setBasicSalary(carrierUpdate.getBasicSalary());
                    carrier.setWorkingDay(carrierUpdate.getWorkingDay());
                    System.out.println("Cập nhật carrier thành công");
                    break;
                }
            }
            writeCarrierList();
        }
    }

    public void updateStoreManager(String account, StoreManager storeManagerUpdate) {
        int index = getIndexByNameCarrier(account);
        if (index < 0) {
            System.out.println("StoreManager not found!");
        } else {
            for (StoreManager storeManager : listStoreManager ){
                if (storeManager.getAccount().equals(account)) {
                    storeManager.setPassword(storeManagerUpdate.getPassword());
                    storeManager.setName(storeManagerUpdate.getName());
                    storeManager.setCoefficient(storeManagerUpdate.getCoefficient());
                    storeManager.setBasicSalary(storeManagerUpdate.getBasicSalary());
                    storeManager.setWorkingDay(storeManagerUpdate.getWorkingDay());
                    System.out.println("Cập nhật store manager thành công");
                    break;
                }
            }
            writeStoreManagerList();
        }
    }

    public void displaySalaryAllEmployee(){
        readClientList();
        readCarrierList();
        readStoreManagerList();
        for (Carrier carrier : listCarrier) {
            System.out.println("name: "+carrier.getName()+"| Salary"+carrier.payroll());

        }
        for (StoreManager storeManager : listStoreManager) {
            System.out.println("name: "+storeManager.getName()+"| Salary: "+storeManager.payroll()+"VND");

        }
    }

    public void searchNameUser(String namUser){
        //TODO
    }

    public void timeKeeping(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập account cần chấm công: ");
        String account = scanner.nextLine();
        readCarrierList();
        readStoreManagerList();
        for (StoreManager storeManager : listStoreManager) {
            if(account.equals(storeManager.getAccount())){
                storeManager.setWorkingDay(storeManager.getWorkingDay()+1);
                writeStoreManagerList();
                System.out.println("Đã chấm công cho "+ storeManager.getAccount());
            }
        }
        for (Carrier carrier : listCarrier) {
            if(account.equals(carrier.getAccount())){
                carrier.setWorkingDay(carrier.getWorkingDay()+1);
                writeCarrierList();
                writeCarrierList();
                System.out.println("Đã chấm công cho "+ carrier.getAccount());
            }
        }
    }
}
