package com.driver;

import io.swagger.models.auth.In;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
        this.deliveryTime = convertTime(deliveryTime);
    }

    public static int convertTime(String string){
        String[] arr = string.split(":");
        int time = 0;
        time = Integer.parseInt(arr[0])*60;
        time += Integer.parseInt(arr[1]);
        return time;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
