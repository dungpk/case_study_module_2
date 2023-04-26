import manager.ProductManager;
import manager.ServiceManager;
import model.platform.Product;
import model.platform.TypeProduct;
import model.platform.User;
import model.subclass.*;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static final int DEFAULT_VALUE = -1;
    static final int EXIT = 0;
    static final int LOG_IN = 1;
    static final int REGISTER = 2;
    static final int VIEW_PRODUCT = 3;
    static final int SEARCH_PRODUCT = 4;

    static public ServiceManager serviceManager = new ServiceManager();
    static LoadingThread loadingThread = new LoadingThread();

    /*************************************************************************************
     ******************************** MAIN ***********************************************
     ***************************************************************************************/

    public static void main(String[] args) {

        loadingThread.start();
        int choice = DEFAULT_VALUE;
        while (choice != EXIT) {
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

    static void odderCompletion(){
        for (Client client : serviceManager.listClientInService) {
            System.out.println("Name : "+client.getName());
            System.out.println("===============================");
            System.out.println(client.Cart);
            System.out.println("===============================");
        }
        boolean check = false;
        double value = 0;
        System.out.printf("Nhập tài khoản khách hàng muốn được hoàn thành đơn:");
        String account = scanner.nextLine();
        for (Client client : serviceManager.listClientInService) {
            if(client.getAccount().equals(account)){
                check = true;
                value = client.calTotalMoney();
                client.Cart.clear();
                break;
            }
        }
        if(check){
            serviceManager.hrm = new HRM("temp","052275252","temp","temp");
            serviceManager.hrm.hrmAddNewRevenue(value);
            System.out.println("Đơn hàng đã được thanh toán");
        }else{
            System.out.println("Không tìm thấy khách hàng");
        }
        serviceManager.writeClientList();
    }

    static void clientAddProductToCart() {
        System.out.println("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.println("Nhập số lượng sản phẩm: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        int index = -1;
        for (Client client : serviceManager.listClientInService) {
            if (client.getAccount().equals(serviceManager.client.getAccount())) {
                client.addToCart(name, quantity);
                serviceManager.writeClientList();
                break;
            }
        }
    }

    static void removeProduct() {
        System.out.println("Nhập tên sản phẩm cần xóa: ");
        String name = scanner.nextLine();
        System.out.println("Bạn có chắc chắn muốn xóa:");
        System.out.println("1.YES");
        System.out.println("THE REST OF: NO");
        int choiceRemoveProduct = Integer.parseInt(scanner.nextLine());
        if (choiceRemoveProduct == 1) {
            serviceManager.storeManager.managerRemoveProduct(name);
        } else {
            System.out.println("Hoàn tác! ");
        }

    }

    static void updateQuantityProduct() {
        System.out.println("Nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.println("Nhập số lượng sản phẩm");
        int quantity = Integer.parseInt(scanner.nextLine());
        serviceManager.storeManager.managerUpdateQuantity(name, quantity);
    }

    static void updatePriceProduct() {
        System.out.println("Nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm");
        int price = Integer.parseInt(scanner.nextLine());
        serviceManager.storeManager.managerUpdateQuantity(name, price);
    }

    static void storeManagerAddProduct() {
        final int MALE = 1;
        final int FEMALE = 2;
        final int CHILDREN = 3;
        System.out.println("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.println("Nhập size sản phẩm: ");
        String size = scanner.nextLine();
        System.out.println("Nhập giá của sản phẩm");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập số lượng sản phẩm");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập loại sản phẩm:");

        System.out.println("1.MALE");
        System.out.println("2.FEMALE");
        System.out.println("3.CHILDREN");
        int type = Integer.parseInt(scanner.nextLine());
        TypeProduct.SexType sexType;
        switch (type) {
            case MALE:
                sexType = TypeProduct.SexType.MALE;
                break;
            case FEMALE:
                sexType = TypeProduct.SexType.FEMALE;
                break;
            case CHILDREN:
                sexType = TypeProduct.SexType.CHILDREN;
                break;
            default:
                System.out.println("Nhập sai giá trị");

                return;
        }

        Product product = new Product(name, size, price, quantity, new
                TypeProduct(sexType, "Áo", "xanh"));
        serviceManager.storeManager.managerAddProduct(product);
    }

    static void changePasswordHRM() {
        serviceManager.readClientList();
        for (HRM hrm : serviceManager.listHRMInService) {
            if (hrm.getAccount().equals(serviceManager.accountUser)) {
                System.out.println("Nhập mật khẩu mới");
                String password = scanner.nextLine();
                System.out.println("Nhập lại mật khẩu mới");
                String passwordConfirm = scanner.nextLine();

                if (!password.equals(passwordConfirm)) {
                    System.out.println("Mật khẩu không trùng khớp");
                } else {
                    hrm.setPassword(password);
                    serviceManager.writeHRMList();
                    System.out.println("Thay đổi mật khẩu thành công");
                }
            }
        }
    }

    static void revenueManager() {
        final int SALARY_MEMBER = 1;
        final int REVENUE_CREATION = 2;
        final int DISPLAY_DAY_REVENUE = 3;
        final int TOTAL_REVENUE = 4;
        final int RESET_REVENUE = 5;
        int optionRevenueManager = DEFAULT_VALUE;
        while (optionRevenueManager != EXIT) {
            System.out.println("""
                    ----------------------------------------
                    |1.Hiển thị lương các thành viên       |
                    |2.Tạo doanh thu cho ngày mới          |
                    |3.Hiển thị doanh thu từng ngày        |
                    |4.Hiển thị toàn bộ doanh thu          |         
                    |5.Reset doanh thu                     |
                    |0.EXIT
                    ---------------------------------------|
                    """);
            optionRevenueManager = Integer.parseInt(scanner.nextLine());
            switch (optionRevenueManager) {
                case SALARY_MEMBER:
                    serviceManager.hrm.displaySalaryAllEmployee();
                    break;
                case REVENUE_CREATION:
                    serviceManager.hrm.hrmAddNewRevenue(0);
                    break;
                case DISPLAY_DAY_REVENUE:
                    serviceManager.hrm.hrmDisplayRevenue();
                    break;
                case TOTAL_REVENUE:
                    System.out.println("Tổng Doanh thu: " + serviceManager.hrm.totalRevenue());
                    break;
                case RESET_REVENUE:
                    serviceManager.hrm.hrmClearRevenue();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");


            }
        }
    }

    static void updateUserInformation() {
        System.out.println("Nhập account cần cập nhật thông tin");
        String account = scanner.nextLine();
        System.out.println("Nhập tên cập nhật:");
        String name = scanner.nextLine();
        System.out.println("Nhập ID cập nhật:");
        String id = scanner.nextLine();
        System.out.println("Nhập mật khẩu mới: ");
        String password = scanner.nextLine();
        System.out.println("Nhập hệ số lương");
        double coefficient = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập lương cơ bản: ");
        double basicSalary = Double.parseDouble(scanner.nextLine());
        System.out.println("Nhập số lượng ngày làm việc trong tháng: ");
        int dayWork = Integer.parseInt(scanner.nextLine());
        Client client = new Client(name, id, account, password);
        serviceManager.hrm.updateClient(account, client);

        StoreManager storeManager = new StoreManager(name, id, account, password);
        storeManager.setCoefficient(coefficient);
        storeManager.setBasicSalary(basicSalary);
        storeManager.setWorkingDay(dayWork);
        serviceManager.hrm.updateStoreManager(account, storeManager);

        Carrier carrier = new Carrier(name, id, account, password);
        carrier.setCoefficient(coefficient);
        carrier.setBasicSalary(basicSalary);
        carrier.setWorkingDay(dayWork);
        serviceManager.hrm.updateCarrier(account, carrier);
    }

    static void searchProduct() {
        System.out.println("Nhập từ khóa cần tìm kiếm:");
        Scanner searchProduct = new Scanner(System.in);
        String keyword = searchProduct.nextLine();
        serviceManager.searchProduct(keyword);
    }

    static void displayTypeProduct() {
        final int MALE = 1;
        final int FEMALE = 2;
        final int CHILDREN = 3;
        int choiceTypeProduct = DEFAULT_VALUE;
        while (choiceTypeProduct != EXIT) {
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
            switch (choiceTypeProduct) {
                case MALE:
                    TypeProduct typeProductMale = new TypeProduct(TypeProduct.SexType.MALE, "");
                    ServiceManager.productManagerInService.displayByTypeSex(typeProductMale);
                    break;
                case FEMALE:
                    TypeProduct typeProductFemale = new TypeProduct(TypeProduct.SexType.FEMALE, "");
                    ServiceManager.productManagerInService.displayByTypeSex(typeProductFemale);
                    break;
                case CHILDREN:
                    TypeProduct typeProductChildren = new TypeProduct(TypeProduct.SexType.CHILDREN, "");
                    ServiceManager.productManagerInService.displayByTypeSex(typeProductChildren);
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }
    }

    static Client initialClient(String account, String password) {
        Scanner scannerInitClient = new Scanner(System.in);
        System.out.println("Nhập tên người dùng");
        String name = scannerInitClient.nextLine();
        System.out.println("Nhập ID người dùng");
        String id = scannerInitClient.nextLine();
        return new Client(name, id, account, password);
    }

    static void addUser() {
        int option = DEFAULT_VALUE;
        final int ADD_CLIENT = 1;
        final int ADD_STORE_MANAGER = 2;
        final int ADD_CARRIER = 3;
        System.out.println("""
                ----------------------------------------
                |1.Thêm Client                         |
                |2.Thêm Store Manager                  |
                |3.Thêm Carrier                        |                  
                |0.EXIT                                |
                ---------------------------------------|
                """);
        while (option != EXIT) {
            System.out.println("Nhập lựa chọn");
            option = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter account:");
            String account = scanner.nextLine();
            System.out.println("Nhập mật khẩu: ");
            String passWord = scanner.nextLine();
            System.out.println("Nhập lại mật khẩu: ");
            String passWordConfirm = scanner.nextLine();
            System.out.println("Nhập tên người dùng: ");
            String name = scanner.nextLine();

            ServiceManager.ClassifyUser validate = serviceManager.searchInUserList(account);
            if (passWord.equals(passWordConfirm)) {
                if (validate == ServiceManager.ClassifyUser.EMPTY) {
                    switch (option) {
                        case ADD_CLIENT:
                            Client client = new Client(name, "12345", account, passWord);
                            serviceManager.hrm.userAccountCreation(1, client);
                            break;
                        case ADD_STORE_MANAGER:
                            StoreManager storeManager = new StoreManager(name, "12345", account, passWord);
                            serviceManager.hrm.userAccountCreation(2, storeManager);
                            break;
                        case ADD_CARRIER:
                            Carrier carrier = new Carrier(name, "12345", account, passWord);
                            serviceManager.hrm.userAccountCreation(3, carrier);
                            break;
                        case EXIT:
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ");
                    }
                    System.out.println("Account đã được khởi tạo thành công! ");
                } else {
                    System.out.println(" tên Account đã có người sử dụng");
                }

            } else {
                System.out.println("Mật khẩu không khớp");
            }

        }


    }

    static void accountManager() {
        final int ADD_USER = 1;
        final int REMOVE = 2;
        final int UPDATE_USER_INFORMATION = 3;
        final int DISPLAY_ALL_USER = 4;

        int choiceAccountManager = DEFAULT_VALUE;
        while (choiceAccountManager != EXIT) {
            System.out.println("""
                    ----------------------------------------
                    |1.Thêm người dùng                     |
                    |2.xóa người dùng                      |
                    |3.cập nhật thông tin người dùng       |
                    |4.Hiển thị toàn bộ thành viên         |         
                    |0.EXIT                                |
                    ---------------------------------------|
                    """);

            System.out.println("Nhập lựa chọn: ");
            choiceAccountManager = Integer.parseInt(scanner.nextLine());


            switch (choiceAccountManager) {
                case ADD_USER:
                    addUser();
                    break;
                case REMOVE:
                    System.out.println("Nhập tài khoản cần xóa");
                    String account = scanner.nextLine();
                    System.out.println("Bạn có chắc chắn xóa tài khoản:" + account);
                    System.out.println("""
                            ----------------------------------------
                            |1.YES                                 |      
                            |0.NO                                  |
                            ---------------------------------------|
                            """);
                    int option = Integer.parseInt(scanner.nextLine());
                    if (option == 1) {
                        serviceManager.hrm.hrmRemoveUser(account, 1);
                        serviceManager.hrm.hrmRemoveUser(account, 2);
                        serviceManager.hrm.hrmRemoveUser(account, 3);
                    }
                    break;
                case UPDATE_USER_INFORMATION:
                    updateUserInformation();
                    break;
                case DISPLAY_ALL_USER:
                    serviceManager.hrm.displayUserList();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    static void adminHandleLoginSuccess() {
        //TODO
    }


    static void storeManagerHandleLoginSuccess() {
        final int ADD_PRODUCT = 1;
        final int UPDATE_PRODUCT_BY_QUANTITY = 2;
        final int UPDATE_PRODUCT_BY_PRICE = 3;
        final int REMOVE_PRODUCT = 4;

        serviceManager.storeManager = new StoreManager
                (ServiceManager.nameUser, "temp_id", ServiceManager.accountUser, "temp_password");
        int optionStoreHandleHandle = DEFAULT_VALUE;
        while (optionStoreHandleHandle != EXIT) {
            System.out.println("""
                    ----------------------------------------
                    |1.Thêm sản phẩm                       |
                    |2.Update số lượng sản phẩm            |
                    |3.Update giá sản phẩm                 |
                    |4 Xóa sản phẩm                        |
                    |5.Đổi mật khẩu                        |
                    |0.LOGOUT                              |
                    ---------------------------------------|
                    """);
            System.out.println("Nhập lựa chọn");
            optionStoreHandleHandle = Integer.parseInt(scanner.nextLine());
            switch (optionStoreHandleHandle) {
                case ADD_PRODUCT:
                    storeManagerAddProduct();
                    break;
                case UPDATE_PRODUCT_BY_QUANTITY:
                    updateQuantityProduct();
                    break;
                case UPDATE_PRODUCT_BY_PRICE:
                    updatePriceProduct();
                    break;
                case REMOVE_PRODUCT:
                    removeProduct();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    static void carrierHandleLoginSuccess() {
        final int ORDER_COMPLETION = 1;
        serviceManager.carrier = new Carrier
                (ServiceManager.nameUser, "temp_id", ServiceManager.accountUser, "temp_password");
        System.out.println(ServiceManager.nameUser + ServiceManager.accountUser);
        int optionCarrierHandle = DEFAULT_VALUE;
        while(optionCarrierHandle!=EXIT){
            System.out.println("""
                    ----------------------------------------
                    |1.Hoàn thành đơn đặt hàng             |   
                    |2.Đổi mật khẩu                        |
                    |0.LOG OUT                              |
                    ---------------------------------------|
                    """);
            System.out.println("Nhập lựa chọn: ");
            optionCarrierHandle = Integer.parseInt(scanner.nextLine());
            switch (optionCarrierHandle){
                case ORDER_COMPLETION:
                    odderCompletion();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.printf("Lựa chọn chưa hợp lệ");
            }
        }
    }

    static void clientHandleLoginSuccess() {
        final int ADD_FINAL = 1;
        serviceManager.client = new Client
                (ServiceManager.nameUser, "temp_id", ServiceManager.accountUser, "temp_password");
        System.out.println(ServiceManager.nameUser + ServiceManager.accountUser);
        int optionClientHandle = DEFAULT_VALUE;
        while (optionClientHandle != EXIT) {
            System.out.println("""
                    ----------------------------------------
                    |1.Thêm sản phẩm vào giỏ               |
                    |2.Đổi mật khẩu                        |
                    |0.LOGOUT                              |
                    ---------------------------------------|
                    """);
            System.out.println("Nhập lựa chọn: ");
            optionClientHandle = Integer.parseInt(scanner.nextLine());
            switch (optionClientHandle) {
                case ADD_FINAL:
                    clientAddProductToCart();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }

        }
    }

    static void hrmHandleLoginSuccess() {
        serviceManager.hrm = new HRM(ServiceManager.nameUser, "temp_id", ServiceManager.accountUser, "temp_password");
        final int ACCOUNT_MANAGER = 1;
        final int REVENUE_MANAGER = 2;
        final int TIME_KEEPING = 3;
        final int EMPLOYEE_SALARY = 4;
        final int PASSWORD_HRM_CHANGE = 5;
        int choiceHRMHandleLogin = DEFAULT_VALUE;
        Scanner scannerHRMHandleLogin = new Scanner(System.in);
        while (choiceHRMHandleLogin != EXIT) {
            System.out.println("""
                    ----------------------------------------
                    |1.Quản lý tài khoản                   |
                    |2.Quản lý doanh số bán hàng           |
                    |3.Chấm công                           |
                    |4.Kiểm tra lương của thành viên       |
                    |5.Đổi mật khẩu                        |
                    |0.LOGOUT                              |
                    ---------------------------------------|
                    """);
            System.out.println("Nhập lựa chọn");
            choiceHRMHandleLogin = Integer.parseInt(scannerHRMHandleLogin.nextLine());
            switch (choiceHRMHandleLogin) {
                case ACCOUNT_MANAGER:
                    accountManager();
                    break;
                case REVENUE_MANAGER:
                    revenueManager();
                    break;
                case TIME_KEEPING:
                    serviceManager.hrm.timeKeeping();
                    break;
                case EMPLOYEE_SALARY:
                    serviceManager.hrm.displaySalaryAllEmployee();
                    break;
                case PASSWORD_HRM_CHANGE:
                    changePasswordHRM();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");

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

    static void registerMember() {
        final int LOGIN = 1;
        Scanner scannerRegister = new Scanner(System.in);
        int choiceRegister = DEFAULT_VALUE;
        while (choiceRegister != EXIT) {
            System.out.println("Enter account:");
            String account = scannerRegister.nextLine();
            System.out.println("Nhập mật khẩu: ");
            String passWord = scannerRegister.nextLine();
            System.out.println("Nhập lại mật khẩu: ");
            String passWordConfirm = scannerRegister.nextLine();

            ServiceManager.ClassifyUser validate = serviceManager.searchInUserList(account);
            if (passWord.equals(passWordConfirm)) {
                if (validate == ServiceManager.ClassifyUser.EMPTY) {
                    Client client = initialClient(account, passWord);
                    serviceManager.addNewClient(client);
                    System.out.println("Account đã được khởi tạo thành công! ");
                } else {
                    System.out.println(" tên Account đã có người sử dụng");
                }

            } else {
                System.out.println("Mật khẩu không khớp");
            }


            while (choiceRegister != LOGIN && choiceRegister != EXIT) {
                System.out.println("""
                        ----------------------------------------
                        |1.Tạo lại tài khoản Client            |
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

        ServiceManager.ClassifyUser loginValidate = serviceManager.validateTypeAccountWhenLogin(account, password);
        if (loginValidate != null) {
            switch (loginValidate) {
                case ADMIN:
                    System.out.println("Hello ADMIN: " + ServiceManager.nameUser);
                    adminHandleLoginSuccess();
                    break;
                case HRM:
                    System.out.println("HR: " + ServiceManager.nameUser);
                    hrmHandleLoginSuccess();
                    break;
                case STORE_MANAGER:
                    System.out.println("store manager: " + ServiceManager.nameUser);
                    storeManagerHandleLoginSuccess();
                    break;
                case CARRIER:
                    System.out.println("carrier: " + ServiceManager.nameUser);
                    carrierHandleLoginSuccess();
                    break;
                case CLIENT:
                    System.out.println("client: " + ServiceManager.nameUser);
                    clientHandleLoginSuccess();
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Sai tai khoan hoac mat khau");
        }

    }

    static void displayProduct() {
        final int DISPLAY_ALL_PRODUCT = 1;
        final int DISPLAY_TYPE_PRODUCT = 2;
        final int DISPLAY_PRODUCT_INCREASE = 3;
        int choiceDisplayProduct = DEFAULT_VALUE;
        while (choiceDisplayProduct != EXIT) {
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
            switch (choiceDisplayProduct) {
                case DISPLAY_ALL_PRODUCT:
                    serviceManager.displayAllProduct();
                    break;
                case DISPLAY_TYPE_PRODUCT:
                    displayTypeProduct();
                    break;
                case DISPLAY_PRODUCT_INCREASE:
                    ServiceManager.productManagerInService.sortByPrice();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }
    }

    static class LoadingThread extends Thread {
        @Override
        public void run() {
            int percent = 0;
            while (percent <= 100) {
                int numBars = percent / 5; // tính toán số lượng ký tự "|" cần in ra
                String bars = String.join("", Collections.nCopies(numBars, "\u001B[33m|\u001B[0m")); // tạo chuỗi chứa ký tự "|"
                System.out.print("\r" + bars + " " + percent + "%"); // in ra chuỗi và phần trăm hoàn thành
                percent++; // tăng biến số phần trăm hoàn thành lên 1
                try {
                    Thread.sleep(4); // tạm dừng 50ms trước khi in ra % loading tiếp theo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\nĐã Tải Xong \u001B[32m✓\u001B[0m");
        }
    }

}