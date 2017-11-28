/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatlogin;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Hp
 */
public class Chat15SINC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //user1 gửi đến server yêu cầu login và nội dung là password 123456
        MyMessage m = new MyMessage();
        m.sender = "user1";
        m.receiver = "server";
        m.type = "login";
        m.content = "123456";
        
        ObjectMapper mapper = new ObjectMapper();
        try{
            String s = mapper.writeValueAsString(m);
            System.out.println("" + s);
            
            MyMessage m1 = mapper.readValue(s, MyMessage.class);
            System.out.println("sender " + m1.sender);
            System.out.println("receiver " + m1.receiver);
            System.out.println("type " + m1.type);
            System.out.println("content " + m1.content);
            
            //System.out.println("" + m.sender + "|" + m.receiver + "|" + m.type + "|" + m.content);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
