/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatlogin;

import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Hp
 */
public class ChatClient {
    public String address = "localhost";
    public int port = 2468;
    
    public MyMessage send(MyMessage m){
        try{
            Socket clientSocket = new Socket(address, port);
            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(m);
            
            OutputStream os = clientSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(s);
            oos.flush();
            
            InputStream is = clientSocket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String s1 = (String) ois.readObject();
            System.out.println("" + s1);
            
            MyMessage m1 = mapper.readValue(s1, MyMessage.class);
            return m1;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println("This is client");
        String address = "localhost";
        int port = 1551;
        
        try{
            Socket clientSocket = new Socket(address, port);
            
            String s = "hello server";
            
            OutputStream os = clientSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(s);
            oos.flush();
            
            InputStream is = clientSocket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            String s1 = (String) ois.readObject();
            System.out.println("" + s1);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
