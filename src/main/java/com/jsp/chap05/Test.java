package com.jsp.chap05;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        JdbcBasic jdbc = new JdbcBasic();

//        jdbc.insert(new Person(300, "조둥짝", 29));
//        jdbc.insert(new Person(400, "최태풍", 34));

//        jdbc.delete(400);

//        jdbc.update(300,"민둥산", 19);

        List<Person> people = jdbc.findAll();
        System.out.println("people = " + people);
    }

}
