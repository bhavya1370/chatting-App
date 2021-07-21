package com.bhavya.ntccmain.modles;

public class User {

    String pfPic, userName, password, uId, lastMessage, mail;

    public User(String pfPic, String userName, String password, String uId, String lastMessage, String mail) {
        this.pfPic = pfPic;
        this.userName = userName;
        this.password = password;
        this.uId = uId;
        this.lastMessage = lastMessage;
        this.mail = mail;
    }

    public User(){}

    //for signUp
    public User(String userName, String password, String mail) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
    }

    public String getPfPic() {
        return pfPic;
    }

    public void setPfPic(String pfPic) {
        this.pfPic = pfPic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
