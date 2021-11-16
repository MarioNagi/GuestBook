package com.kimbshkorp.guestbook;

public class User {

    private String uName, uMail, uPhone, uCompany, uProduct;

    public User() {
    }

    public User(String uName, String uMail, String uPhone, String uCompany, String uProduct) {

        this.uName = uName;
        this.uMail = uMail;
        this.uPhone = uPhone;
        this.uCompany = uCompany;
        this.uProduct = uProduct;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuMail() {
        return uMail;
    }

    public void setuMail(String uMail) {
        this.uMail = uMail;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuCompany() {
        return uCompany;
    }

    public void setuCompany(String uCompany) {
        this.uCompany = uCompany;
    }

    public String getuProduct() {
        return uProduct;
    }

    public void setuProduct(String uProduct) {
        this.uProduct = uProduct;
    }
}
