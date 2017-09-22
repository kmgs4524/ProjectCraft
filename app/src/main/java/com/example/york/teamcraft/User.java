package com.example.york.teamcraft;

/**
 * Created by York on 2017/9/22.
 */

public class User {
    private String email;
    private String name;
    private String password;

    public User(String n, String e, String p) {
        name = n;
        email = e;
        password = p;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
