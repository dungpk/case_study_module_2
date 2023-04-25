package model.platform;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String id;
    private TypeUser type;
    private String account;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String name, String id, TypeUser type, String account, String password) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.account = account;
        this.password = password;
    }

    public User(String name, String id, String account, String password) {
        this.name = name;
        this.id = id;
        this.account = account;
        this.password = password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return
                "name: " + name +"\n" + "id: " + id  +"\n" +
                " type: " + type +
                " account: " + account + "\n" ;
    }
}
