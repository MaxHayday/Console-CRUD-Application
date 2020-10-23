package com.max_hayday.console_crud_application;

import com.max_hayday.console_crud_application.view.UserView;

import java.io.IOException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        UserView view = new UserView();
        view.showUserView();
    }
}
