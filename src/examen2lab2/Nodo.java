/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2lab2;

/**
 *
 * @author Ana Reyes
 */
public class Nodo {
    
    String username;
    long pos;
    Nodo siguiente;

    public Nodo(String username, long pos) {
        this.username = username;
        this.pos = pos;
        this.siguiente = null;
    }
}

