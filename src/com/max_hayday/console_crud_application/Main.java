package com.max_hayday.console_crud_application;

import com.max_hayday.console_crud_application.view.RegionView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        RegionView view = new RegionView();
        view.showMenu();

    }
}
