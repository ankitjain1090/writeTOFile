package com.writetoexcel.model;

import com.writetoexcel.util.DateUtil;

import java.util.ArrayList;

public class CustomerData {

    String name;
    int age ;
    String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTime() {
        return DateUtil.getLocalDateTime();
    }

    private void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Object> getCustomerData(){
        ArrayList<Object> customerData = new ArrayList<Object>();
        customerData.add(getName());
        customerData.add(getAge());
        customerData.add(getTime());
        return customerData;
    }

    public ArrayList<String> getClassVariable(){
        ArrayList<String> classData = new ArrayList<String>();
        classData.add("NAME");
        classData.add("AGE");
        classData.add("TIME");
        return classData;
    }
}
