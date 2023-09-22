/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2lab2;

/**
 *
 * @author Ana Reyes
 */
public class User {

    private String username;
    private long pos;

    public User(String username, long pos) {
        this.username = username;
        this.pos = pos;
    }

    public String getUsername() {
        return username;
    }

    public long getPos() {
        return pos;
    }
}

