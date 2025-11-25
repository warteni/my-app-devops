package org.example;

/**
 * Application DevOps - Bonjour Malek
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(getMessage());
    }

    public static String getMessage() {
        return "🚀 Bonjour DevOps - CI/CD avec Jenkins, Maven et Tomcat !";
    }

    public String greet(String name) {
        return "Bonjour " + name + " !";
    }
}