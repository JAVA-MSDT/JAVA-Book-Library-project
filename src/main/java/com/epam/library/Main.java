package com.epam.library;


public class Main {
    public static void main(String[] args) {

        String order = "2015-11-25";
        String returning = "2014-11-24";
        if(order.compareToIgnoreCase(returning) > 0){
            System.out.println("returning is old");
        }else {
            System.out.println("order is old");
        }

    }

}
