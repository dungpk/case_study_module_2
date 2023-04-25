import fileIO.ReadToFile;
import fileIO.WriteToFile;
import manager.ProductManager;
import manager.ServiceManager;
import model.platform.Product;
import model.platform.TypeProduct;
import model.platform.User;
import model.revenue.Revenue;
import model.revenue.RevenueManager;
import model.subclass.*;

import java.util.*;
import java.util.stream.Collectors;


public class Main {


    static final int DEFAULT_VALUE = -1;
    static final int EXIT = 0;
    static final int LOG_IN =  1;
    static final int REGISTER = 2;
    static final int VIEW_PRODUCT = 3;
    static final int SEARCH_PRODUCT = 4;

    static public ServiceManager serviceManager = new ServiceManager();
    static Scanner scanner = new Scanner(System.in);

    /*************************************************************************************
     ******************************** MAIN ***********************************************
     ***************************************************************************************/

    public static void main(String[] args) {

        int choice = DEFAULT_VALUE;
        while (choice!=EXIT){
            Scanner mainScanner = new Scanner(System.in);
            displayHomePage();
            System.out.println("enter selection:");
            choice = Integer.parseInt(mainScanner.nextLine());
            switch (choice) {
                case LOG_IN:
                    displayLogIn();
                    break;
                case REGISTER:
                    registerMember();
                    break;
                case VIEW_PRODUCT:
                    displayProduct();
                    break;
                case SEARCH_PRODUCT:
                    searchProduct();
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn nhập không hợp lệ ");
                    break;
            }
        }
    }

    /*************************************************************************************
    *************************** SUPPORT FUNCTION  ***************************************
    ***************************************************************************************/

    static int searchUserInList(){
        return 0;
    }
    static void searchProduct(){
        System.out.println("Nhập từ khóa cần tìm kiếm:");
        Scanner searchProduct = new Scanner(System.in);
        String keyword = searchProduct.nextLine();
        serviceManager.searchProduct(keyword);
    }
    static void displayTypeProduct(){
        final int MALE = 1;
        final int FEMALE = 2;
        final int CHILDREN = 3;
        int choiceTypeProduct = DEFAULT_VALUE;
        while(choiceTypeProduct != EXIT){
            System.out.println("""
                ------------------
                |1.MALE          |
                |2.FEMALE        |
                |3.CHILDREN      |
                |0.Exit"         |
                -----------------|
                """);
            Scanner scannerDisplayTypeProduct = new Scanner(System.in);
            choiceTypeProduct = Integer.parseInt(scannerDisplayTypeProduct.nextLine());
            switch (choiceTypeProduct){
                case MALE:
                    TypeProduct typeProductMale = new TypeProduct(TypeProduct.SexType.MALE,"");
                    serviceManager.productManager.displayByTypeSex(typeProductMale);
                    break;
                case FEMALE:
                    TypeProduct typeProductFemale = new TypeProduct(TypeProduct.SexType.FEMALE,"");
                    serviceManager.productManager.displayByTypeSex(typeProductFemale);
                    break;
                case CHILDREN:
                    TypeProduct typeProductChildren = new TypeProduct(TypeProduct.SexType.CHILDREN,"");
                    serviceManager.productManager.displayByTypeSex(typeProductChildren);
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }
    }


    /***************************************************************************************
     ****************************** MAIN FUNCTION ******************************************
     ***************************************************************************************/

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
    static Client initialClient(String account,String password){
        Scanner  scannerInitClient = new Scanner(System.in);
        System.out.println("Nhập tên người dùng");
        String name = scannerInitClient.nextLine();
        System.out.println("Nhập ID người dùng");
        String id = scannerInitClient.nextLine();
        return new Client(name,id,account,password);
    }

    static void registerMember() {
        final int LOGIN = 1;
        Scanner scannerRegister = new Scanner(System.in);
        int choiceRegister = DEFAULT_VALUE;
        while(choiceRegister!=EXIT){
            System.out.println("Enter account:");
            String account = scannerRegister.nextLine();
            System.out.println("Nhập mật khẩu: ");
            String passWord = scannerRegister.nextLine();
            System.out.println("Nhập lại mật khẩu: ");
            String passWordConfirm = scannerRegister.nextLine();

            ServiceManager.ClassifyUser  validate = serviceManager.searchInUserList(account);
            if(validate == ServiceManager.ClassifyUser.EMPTY){

                Client client = initialClient(account,passWord);
                serviceManager.addNewClient(client);
                System.out.println("Tên account đã được khởi tạo");
            }
            else{
                if(passWord.equals(passWordConfirm)){
                    System.out.println("Đã tạo tài khoản thành công");
                }else{
                    System.out.println("Mật khẩu không khớp");
                }
            }
            while (choiceRegister!=LOGIN && choiceRegister!=EXIT){
                System.out.println("""
                ----------------------------------------
                |1.Tạo lại tài khoản                   |
                |0.EXIT                                |
                ---------------------------------------|
                """);
                System.out.println("Nhập lựa chọn");
                choiceRegister = Integer.parseInt(scannerRegister.nextLine());

            }
        }

    }

    static void displayLogIn() {
        Scanner scannerLogIn = new Scanner(System.in);
        System.out.println("Nhập tài khoản: ");
        String account = scannerLogIn.nextLine();
        System.out.println("Nhập mật khẩu:");
        String password = scannerLogIn.nextLine();
    }

    static void displayProduct(){
        final int DISPLAY_ALL_PRODUCT = 1;
        final int  DISPLAY_TYPE_PRODUCT = 2;
        final int DISPLAY_PRODUCT_INCREASE = 3;
        int choiceDisplayProduct = DEFAULT_VALUE;
        while (choiceDisplayProduct != EXIT){
            System.out.println("""
                ----------------------------------------
                |1.Hiển thị toàn bộ sản phẩm           |
                |2.Hiển thị sản phẩm theo loại         |
                |3.Hiển thị sản phẩm theo giá tăng dần |
                |0.EXIT                                |
                ---------------------------------------|
                """);
            Scanner scannerDisplayProduct = new Scanner(System.in);
            System.out.println("Nhập lựa chọn");
            choiceDisplayProduct = Integer.parseInt(scannerDisplayProduct.nextLine());
            switch (choiceDisplayProduct){
                case DISPLAY_ALL_PRODUCT:
                    serviceManager.displayAllProduct();
                    break;
                case DISPLAY_TYPE_PRODUCT:
                    displayTypeProduct();
                    break;
                case DISPLAY_PRODUCT_INCREASE:
                    serviceManager.productManager.sortByPrice();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }
    }


}