package dk.bringlarsen.exploration.java.model;

public non-sealed class Cat implements Animal {

    @Override
    public void walk() {
        System.out.println("walk");
    }
}
