/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2lab2;

/**
 *
 * @author Ana Reyes
 */
public class HashTable {

    private Nodo inicio = null;
    private int size = 0;

    public boolean isEmpty() {
        return inicio == null;
    }

    public void add(String username, long pos) {
        Nodo newNode = new Nodo(username, pos);
        if (isEmpty()) {
            inicio = newNode;
        } else {
            Nodo tmp = inicio;
            while (tmp.siguiente != null) {
                tmp = tmp.siguiente;
            }
            tmp.siguiente = newNode;
        }
        size++;
    }

    public boolean remove(String username) {
        if (!isEmpty()) {
            if (inicio.username.equals(username)) {
                inicio = inicio.siguiente;
                size--;
                return true;
            } else {
                Nodo tmp = inicio;
                while (tmp.siguiente != null) {
                    if (tmp.siguiente.username.equals(username)) {
                        tmp.siguiente = tmp.siguiente.siguiente;
                        size--;
                        return true;
                    }
                    tmp = tmp.siguiente;
                }
            }
        }
        return false;
    }

    public long search(String username) {
        Nodo tmp = inicio;
        while (tmp != null) {
            if (tmp.username.equals(username)) {
                return tmp.pos;
            }
            tmp = tmp.siguiente;
        }
        return -1;
    }

    public void print() {
        Nodo tmp = inicio;
        while (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.siguiente;
        }
    }
 public  enum Trophy {
        PLATINO(5),
        ORO(3),
        PLATA(2),
        BRONCE(1);

        public int points;

        Trophy(int points) {
            this.points = points;
        }
    }

   
}
