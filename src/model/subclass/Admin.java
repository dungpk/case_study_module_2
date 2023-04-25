package model.subclass;

import model.platform.TypeUser;
import model.platform.User;

import java.io.Serializable;

public class Admin extends User  implements Serializable {
    public Admin() {
    }

    public Admin(String name, String id, String account, String password) {
        super(name, id,new TypeUser(TypeUser.ClassifyUser.ADMIN, TypeUser.Sex.FEMALE), account, password);
    }
}