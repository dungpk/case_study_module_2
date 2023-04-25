package model.platform;

import java.io.Serializable;


public class TypeProduct implements Serializable {
    public enum SexType{
        MALE,FEMALE,CHILDREN;
    }

    private SexType sexType;
    private String nameType;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    public String getNameType() {
        return nameType;
    }

    public TypeProduct(SexType sexType, String nameType, String color) {
        this.sexType = sexType;
        this.nameType = nameType;
        this.color = color;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public TypeProduct(SexType sexType, String nameType) {
        this.sexType = sexType;
        this.nameType = nameType;
    }

    public TypeProduct() {
    }

    @Override
    public String toString() {
        return
                "sexType: " + sexType +
                ", nameType: '" + nameType + '\'' +
                ", color: '" + color + '\'' +
                '}';
    }
}