package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
       CustomClassLoader customClassLoader  = new CustomClassLoader();
        try {
            Class aClass =  customClassLoader.findClass("com.company.A");
            Object a = aClass.newInstance();
            aClass.getDeclaredMethod("hello").invoke(a);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
