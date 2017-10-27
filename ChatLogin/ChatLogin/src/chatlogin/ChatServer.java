/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatlogin;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Hp
 */
public class ChatServer {
    public static void main(String[] args) {
        System.out.println("This is server");
        int port = 2468;
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket currentSocket = serverSocket.accept();
                System.out.println("Connected");
                
                InputStream is = currentSocket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                String s = (String) ois.readObject();
                String s1 = "fail";
                
                //xử lý
                ObjectMapper mapper = new ObjectMapper();
                MyMessage m = mapper.readValue(s, MyMessage.class);
                
                if (m.type.equalsIgnoreCase("login")){
                    if (m.sender.equalsIgnoreCase("songoku")){
                        if (m.content.equalsIgnoreCase("dragon ball")){
                            s1 = "ok";
                        }
                        else{
                            s1 = "fail";
                        }
                    }
                    else if (m.sender.equalsIgnoreCase("tokuda")){
                        if (m.content.equalsIgnoreCase("shigeo")){
                            s1 = "ok";
                        }
                        else{
                            s1 = "fail";
                        }
                    }
                    else if (m.sender.equalsIgnoreCase("kokomi")){
                        if (m.content.equalsIgnoreCase("mi an lien")){
                            s1 = "ok";
                        }
                        else{
                            s1 = "fail";
                        }
                    }
                    else{
                        s1 = "fail";
                    }
                }
                
                System.out.println("" + s);
                //xử lý 
                MyMessage m1 = new MyMessage();
                m1.sender = "server";
                m1.receiver = m.sender;
                m1.type = "login";
                m1.content = s1;
                
                s1 = mapper.writeValueAsString(m1);
                OutputStream os = currentSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(s1);
                oos.flush();
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
