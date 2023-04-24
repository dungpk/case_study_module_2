package model.revenue;

import java.io.Serializable;

public class Revenue  implements Serializable {
    private double dayRevenue;
    private int day;

    @Override
    public String toString() {
        return "Revenue{" +
                "dayRevenue=" + dayRevenue +
                ", day=" + day +
                '}';
    }

    public double getDayRevenue() {
        return dayRevenue;
    }

    public void setDayRevenue(double dayRevenue) {
        this.dayRevenue = dayRevenue;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Revenue(double dayRevenue, int day) {
        this.dayRevenue = dayRevenue;
        this.day = day;
    }
}
