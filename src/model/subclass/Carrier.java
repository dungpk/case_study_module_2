package model.subclass;

import base.Payroll;
import model.platform.TypeUser;
import model.platform.User;
import model.revenue.RevenueManager;

import java.io.Serializable;

public class Carrier extends User implements Serializable, Payroll {
    RevenueManager carrierRevenue = new RevenueManager();
    @Override
    public double payroll(){

        return basicSalary*workingDay + coefficient*carrierRevenue.totalRevenue();
    }
    private double  basicSalary = 100000;
    private double workingDay = 30;
    private double coefficient = 0.3;

    public Carrier(String name, String id, String account, String password){
        super(name, id,new TypeUser(TypeUser.ClassifyUser.CARRIER, TypeUser.Sex.MALE) ,account,password);
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

    @Override
    public String toString() {
        return "Carrier: " +
                super.toString()+
                " basicSalary: " + basicSalary +"\n"+
                " workingDay: " + workingDay +"\n"+
                " coefficient: " + coefficient +"\n"
                ;
    }
}
