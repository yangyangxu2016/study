package com.example.network.nio.catalina.servlets;

import com.example.network.nio.catalina.http.GPRequest;
import com.example.network.nio.catalina.http.GPResponse;
import com.example.network.nio.catalina.http.GPServlet;

import java.io.UnsupportedEncodingException;

public class MyServlet extends GPServlet {

    @Override
    public void doGet(GPRequest request, GPResponse response) {
        try {
            response.write(request.getParameter("name"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doPost(GPRequest request, GPResponse response) {

    }
}
