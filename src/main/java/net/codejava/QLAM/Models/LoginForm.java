package net.codejava.QLAM.Models;

public class LoginForm {
    private String userName;
    private String password;

    public LoginForm() {
    }

    public LoginForm(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
