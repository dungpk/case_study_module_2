import fileIO.ReadToFile;
import fileIO.WriteToFile;
import manager.ProductManager;
import manager.ServiceManager;
import model.platform.Product;
import model.platform.TypeProduct;
import model.platform.User;
import model.revenue.Revenue;
import model.revenue.RevenueManager;
import model.subclass.Carrier;
import model.subclass.Client;
import model.subclass.HRM;
import model.subclass.StoreManager;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static final int DEFAULT_VALUE = -1;
    static final int EXIT = 0;
    static final int LOG_IN = 1;
    static final int REGISTER = 2;
    static final int VIEW_PRODUCT = 3;

    static final int SEARCH_PRODUCT =4;



    static void displayHomePage() {
        System.out.println("""
                ------------------
                |1.Log In        |
                |2.Register      |
                |3.View Product  |
                |4.Search Product|
                |0.Exit"         |
                -----------------|
                """);
    }


    static void registerMember() {
        ReadToFile<Product> readDataToFile = new ReadToFile<>();
        List<Product> product = readDataToFile.readToFile("client.txt");
        System.out.println("Enter account:");
        Scanner scanner = new Scanner(System.in);
        String account = scanner.nextLine();
    }

    static void displayLogIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tài khoản: ");
        String account = scanner.nextLine();
        System.out.println("Nhập mật khẩu:");
        String password = scanner.nextLine();
    }

    public static void main(String[] args) {
        HRM hrm = new HRM("phung khac dung","1432","dungphung","123456");
        ServiceManager serviceManager = new ServiceManager();
        displayHomePage();
        Scanner scanner = new Scanner(System.in);
        int choice = DEFAULT_VALUE;
        while (choice!=EXIT){
            System.out.println("enter selection:");
            choice = scanner.nextInt();
            switch (choice) {
                case LOG_IN:
                    displayLogIn();
                    break;
                case REGISTER:
                    registerMember();
                    break;
                case VIEW_PRODUCT:
                    serviceManager.displayAllProduct();
                    break;
                case SEARCH_PRODUCT:
                    TypeProduct typeProduct = new TypeProduct(TypeProduct.SexType.MALE,"Áo");
                    serviceManager.displayTypeProduct(typeProduct);
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn nhập không hợp lệ ");
                    break;
            }
        }


    }
}