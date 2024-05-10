package com.jsp.chap05;

public class Test {
    public static void main(String[] args) {

        JdbcBasic jdbc = new JdbcBasic();

//        jdbc.insert(new Person(300, "조둥짝", 29));
//        jdbc.insert(new Person(400, "최태풍", 34));

        jdbc.delete(400);
    }

}
