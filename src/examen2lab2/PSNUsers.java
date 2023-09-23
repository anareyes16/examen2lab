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

    private RandomAccessFile raf;
    private Hashtable<String, User> users;

    public PSNUsers(String fileName) throws IOException {
        raf = new RandomAccessFile(fileName, "rw");
        users = new Hashtable<>();
        reloadHashTable();
    }

    private void reloadHashTable() throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            long position = raf.getFilePointer();
            String username = raf.readUTF();
            boolean isActive = raf.readBoolean();

            if (isActive) {
                users.put(username, new User(username, position));
            }
        }
    }

    public void addUser(String username) throws IOException {
        if (users.containsKey(username)) {
            System.out.println("Usuario Existente.");
            return;
        }

        long position = raf.length();
        raf.seek(position);
        raf.writeUTF(username);
        raf.writeBoolean(true);

        users.put(username, new User(username, position));
    }

    public void deactivateUser(String username) {
        User user = users.get(username);
        if (user == null) {
            System.out.println("Usuario no existente");
            return;
        }

        try {
            long position = user.getPos();
            raf.seek(position);
            raf.writeBoolean(false);
            users.remove(username);
            System.out.println("Se desactivó el usuario con éxito.");
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
    }

    public void addTrophyTo(String username, String trophyGame, String trophyName, Trophy type) throws IOException {
        User user = users.get(username);
        if (user == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        try (RandomAccessFile trophiesRaf = new RandomAccessFile("psn_trophies.dat", "rw")) {
            trophiesRaf.seek(trophiesRaf.length());
            trophiesRaf.writeLong(user.getPos());
            trophiesRaf.writeUTF(type.name());
            trophiesRaf.writeUTF(trophyGame);
            trophiesRaf.writeUTF(trophyName);
        }
    }
    
    public void playerInfo(String username) throws IOException {
    User user = users.get(username);
    if (user == null) {
        System.out.println("Usuario no encontrado.");
        return;
    }

    System.out.println("Datos del usuario:");
    System.out.println("Nombre de usuario: " + user.getUsername());
    System.out.println("Puntos acumulados: " + user.getTrophyPoints());
 

    System.out.println("\nTrofeos del usuario:");
    try (RandomAccessFile trophiesRaf = new RandomAccessFile("psn_trophies.dat", "rw")) {
        trophiesRaf.seek(0);
        while (trophiesRaf.getFilePointer() < trophiesRaf.length()) {
            long userPos = trophiesRaf.readLong();
            if (userPos == user.getPos()) {
                String trophyType = trophiesRaf.readUTF();
                String trophyGame = trophiesRaf.readUTF();
                String trophyDescription = trophiesRaf.readUTF();
            }}}
