/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2lab2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ana Reyes
 */
public class FileManager {
    
    
    
    
     private RandomAccessFile RAF;
    private Map<String, User> users;

    public FileManager(String filename) throws IOException {
        RAF = new RandomAccessFile(filename, "rw");
        users = new HashMap<>();
    }

    public void addUser(String username) throws IOException {
     
        User user = new User(username, RAF.length());

    
        RAF.seek(RAF.length());
        RAF.writeInt(-1);
        RAF.writeUTF(username);
        RAF.writeBoolean(true);

       
        users.put(username, user);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}