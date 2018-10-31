package com.example.catalina.servlets;

import com.example.catalina.http.GPRequest;
import com.example.catalina.http.GPResponse;
import com.example.catalina.http.GPServlet;

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
