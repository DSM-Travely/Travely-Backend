package com.example.travely.service;

import com.example.travely.exception.UserNotFoundException;

public class Service {

    public void a(int a) {
        if(a != 0) {
            throw new UserNotFoundException();
        }
    }

}
