package com.itis.templateitis;

import android.util.Log;

class UserJava  {

    private String email;
    private String phone;

    public UserJava(String email) {
        this.email = email;
        this.phone = "";
        Log.e("UserJava", email);
    }

    public UserJava(String email, String phone) {
        this.email = email;
        this.phone = phone;
        Log.e("UserJava", email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
