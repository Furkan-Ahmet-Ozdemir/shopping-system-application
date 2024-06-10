package org.example.id;
public class IDCounter {
    private static int id = 0;
    public static int increaseID(){
        return id++;
    }
}
