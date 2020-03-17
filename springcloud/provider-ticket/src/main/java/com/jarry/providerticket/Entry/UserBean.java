package com.jarry.providerticket.Entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @BelongsProject: spring_cloud_demo
 * @BelongsPackage: com.jarry.providerticket.Entry
 * @Author: Jarry.Chang
 * @CreateTime: 2020-03-16 14:51
 */
@Data
public class UserBean implements Serializable {
    public String name;

    public int age;
    public int phone;
    public int email;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String uuid;
    //ssssss
    public   UserBean(String s,int age,int phone,int email){
        this.name = s;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }
    public UserBean(String s){
        this.name = s;
    }
    public UserBean(String s,int age){
        this.name = s;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return age == userBean.age &&
                phone == userBean.phone &&
                email == userBean.email &&
                Objects.equals(name, userBean.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, phone, email);
    }

    public UserBean(String s, int age, int phone){
        this.name = s;
        //sssssssssdcdsadsadsa
        this.age = age;
        this.phone = phone;
    }
    public UserBean(){

    }
}
