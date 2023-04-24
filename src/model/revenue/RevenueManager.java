package model.revenue;

import fileIO.ReadToFile;
import fileIO.WriteToFile;
import model.platform.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RevenueManager implements Serializable {
    private final String REVENUE = "revenue.txt";
    static ReadToFile<Revenue> readDataToFile = new ReadToFile<>();
    static WriteToFile<Revenue> writeDataToFile = new WriteToFile<>();
    private List<Revenue> revenues = new ArrayList<>();


    public RevenueManager() {
        readRevenue();
    }

    public void displayRevenue() {

        readRevenue();
        if (revenues.isEmpty()) {
            System.out.println("Đã Reset doanh thu tháng");
        } else {
            for (Revenue re : revenues) {
                System.out.println(re);
            }
        }
}

    public void insertRevenue(Revenue revenue) {
        readRevenue();
        if (revenues.isEmpty()) {
            System.out.println("Đã Reset doanh thu tháng");
        }
        int index = getIndexByDay(revenue.getDay());
        if (index >= 0) {
            revenues.set(index,revenue);
        } else {
            revenue.setDay(revenues.size()+1);
            revenues.add(revenue);
        }
        writeRevenue();
    }

    public void clearRevenue() {
        if(revenues.isEmpty()){
            System.out.println("Đã reset toàn bộ doanh thu trong tháng");

        }else{
            writeDataToFile.writeToFile(REVENUE, new ArrayList<>());
            System.out.println("Đã reset toàn bộ doanh thu trong tháng");
            revenues = null;
        }
    }

    public double totalRevenue() {
        readRevenue();
        if(revenues.isEmpty()){
            System.out.println("Đã reset toàn bộ doanh thu trong tháng");
            return 0;
        }
        else{
            double totalRe = 0;
            readRevenue();
            for (Revenue re : revenues) {
                totalRe += re.getDayRevenue();
            }
            return totalRe;
        }
    }
    public void updateRevenue(int day,double newRevenue){
        readRevenue();
        int index = getIndexByDay(day);
        if (index >= 0) {
            revenues.set(index,new Revenue(newRevenue,day));
            writeRevenue();
        } else {
            System.out.println("Doanh thu ngày thứ "+day+ " không tồn tại");
        }

    }

    private int getIndexByDay(int day) {
        int index = -1;
        readRevenue();
        for (Revenue revenue : revenues ) {
            if (revenue.getDay()== day) {
                return revenues.indexOf(revenue);
            }
        }
        return index;
    }

    private void readRevenue() {
        revenues = readDataToFile.readToFile(REVENUE);
    }
    private void writeRevenue(){
        writeDataToFile.writeToFile(REVENUE,revenues);
    }
}

