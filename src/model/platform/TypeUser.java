package model.platform;

import java.io.Serializable;

public class TypeUser implements Serializable {
     public enum  ClassifyUser {
        ADMIN, HRM, STORE_MANAGER, CARRIER, CLIENT;
    }
    public enum Sex{
        MALE,FEMALE;
    };
   ClassifyUser classifyUser;
   Sex sex;

    public TypeUser(ClassifyUser classifyUser, Sex sex) {
        this.classifyUser = classifyUser;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return
                 classifyUser + "\n"+
                " sex: " + sex +"\n"
                ;
    }
}