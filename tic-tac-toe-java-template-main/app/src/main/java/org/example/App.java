package org.example;

public class App {
    public static void main(String[] args) {
      System.out.println(new App().getGreeting());
      new Game().start();
    }
    public String getGreeting() {
      return "Hello World!";
  }

    
}
