package model.subclass;

import base.Payroll;
import manager.ProductManager;
import model.platform.Product;
import model.platform.TypeProduct;
import model.platform.TypeUser;
import model.platform.User;
import model.revenue.RevenueManager;

import java.io.Serializable;

public class StoreManager extends User  implements Serializable, Payroll {
    private double  basicSalary = 150000;
    private double workingDay = 30;
    private double coefficient = 0.3;


    static ProductManager productManager = new ProductManager();
    static RevenueManager ManagerRevenue = new RevenueManager();


    @Override
    public double payroll() {
        return basicSalary*workingDay+coefficient*ManagerRevenue.totalRevenue();
    }

    public StoreManager(String name, String id, String account, String password) {
        super(name, id, new TypeUser(TypeUser.ClassifyUser.STORE_MANAGER, TypeUser.Sex.MALE), account, password);
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(double workingDay) {
        this.workingDay = workingDay;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void searchProduct(String name){
        productManager.searchProduct(name);
    }

    public void managerDisplayAllProduct(){
        productManager.display(null);
    }
    public void managerDisplayTypProduct(TypeProduct type){

        productManager.display(type);
    }

    public void managerAddProduct(Product product){
        int index = productManager.getIndexByName(product.getName());
        if(index >=0){
            System.out.println("Sản phẩm đã tồn tại và không thể thêm");

        }else{
            productManager.insertProduct(product);
        }
    }
    public void managerInsertProduct(Product product){
        int index = productManager.getIndexByName(product.getName());
        if(index >=0){
            productManager.insertProduct(product);
        }else{
            System.out.println("Sản phẩm không tồn tại");
        }
    }

    public void managerRemoveProduct(String name){
        productManager.removeProduct(name);
    }

    public void managerUpdatePrice(String name,int price){
        productManager.updatePriceProduct(name,price);
    }
    public void managerUpdateQuantity(String name,int value){
        productManager.updateQuantityProduct(name,value);
    }

    @Override
    public String toString() {
        return "StoreManager:"  +
                super.toString()+
                "basicSalary=" + basicSalary+"\n" +
                " workingDay: " + workingDay+"\n" +
                " coefficient=" + coefficient+"\n"
                ;
    }
}
