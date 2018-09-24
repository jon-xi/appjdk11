package com.mycompany.app.functionapp;

import java.awt.*;

public class ActionListenDemo {
    public void addButton(){
        Button btn=new Button();
        btn.addActionListener(event -> System.out.println("Clicked"));

    }
}
