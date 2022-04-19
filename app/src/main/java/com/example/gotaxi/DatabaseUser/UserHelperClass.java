package com.example.gotaxi.DatabaseUser;

/*
                        UserHelperClass.java ---> INFORMATION
            ------------------------------------------------------------
            This class manages data from all the intents.
            -------------------------------------------------------------
 */
public class UserHelperClass {
    //Attributes
    private String user_email, password,user_phone, message;
    private boolean isDriver;
    int messageType;

    //constructor - must create empty constructor
    public UserHelperClass() {

    }
    //constructor
    public UserHelperClass( String user_email, String user_phone,String password, String message, int messageType, boolean isDriver) {
        this.user_email = user_email; // email user entered
        this.user_phone = user_phone; //phone number from the user
        this.password = password;
        this.messageType = messageType; //0 -> כללי 1-> תשלום 2-> תיקון
        this.isDriver= isDriver;
        this.message = ""; //staring message from empty
    }

    //methods
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //if we will write the getters as 'isDriver' it will create a problem during running.
    public boolean getIsDriver() { return isDriver; }

    public void setAdmin(boolean isDriver) { this.isDriver = isDriver; }

    //ToString
    public String messageString() {
        return   this.getUser_phone() + " \n" + this.getMessage();

    }
}
