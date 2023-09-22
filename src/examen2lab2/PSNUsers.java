/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2lab2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Hashtable;
import java.util.LinkedList;

public class PSNUsers {

    private RandomAccessFile RAF;
    private Hashtable<String, User> users;

    public PSNUsers(String filename) {
        try {
            RAF = new RandomAccessFile(filename, "rw");
            users = new Hashtable<>();
            reloadHashTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void reloadHashTable() throws IOException {
        users.clear();
        long fileLength = RAF.length();
        RAF.seek(0);

        while (RAF.getFilePointer() < fileLength) {
            int code = RAF.readInt();
            long position = RAF.getFilePointer();
            String username = RAF.readUTF();
            boolean isActive = RAF.readBoolean();

            if (isActive) {
                User userData = new User(username, code);
                users.put(username, userData);
            } else {

                RAF.seek(position);
            }
        }
    }

    public void addUser(String username) throws IOException {
        try {

            if (users.containsKey(username)) {
                System.out.println("El usuario ya existe.");
                return;
            }
            long currentPosition = RAF.length();
            RAF.writeUTF(username);
            RAF.writeBoolean(true);
            User newUser = new User(username);
            users.put(username, newUser);

            System.out.println("Usuario agregado con éxito.");
        } catch (IOException ex) {
            System.out.println("Error al agregar el usuario: " + ex.getMessage());
        }

    }

    void deactivateUser(String username) {
        try {
            long posicionEncontrado = users.search(username);

            if (posicionEncontrado == -1) {
                System.out.println("No se encontro al usuario para desactivarlo.");
                return;
            }
            RAF.seek(posicionEncontrado);
            RAF.writeBoolean(false);
            users.remove(username);
            System.out.println("Se deactivio el usuario con exito.");

        } catch (IOException ex) {
            System.out.println("Algo salio mal al deactivear usuario");
        }
    }
    
    public void addTrophieTo(String username, String trophyGame, String trophyName, Trophy type) {
    try {
 
        long posicionEncontrado = users.search(username);

       
        if (posicionEncontrado == -1) {
            System.out.println("No se encontró al usuario para añadir el trofeo.");
            return;
        }

        RAF.seek(posicionEncontrado + 1);

 RAF.writeUTF(type.name());

    
       RAF.writeUTF(trophyGame);

      
       RAF.writeUTF(trophyName);

       RAF.writeLong(System.currentTimeMillis());

     
        users.get(username).addTrophy(type, trophyGame, trophyName);

     
        System.out.println("Se añadió el trofeo con éxito.");
    } catch (IOException ex) {
        System.out.println("Algo salió mal al añadir el trofeo");
    }

}
