package com;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

    public static void sendMessage() throws Exception{

        Scanner sc=new Scanner(System.in);
        System.out.println("Completa el siguiente mensaje: {farolaNumero:_,valor:_}");
        StringBuilder sb=new StringBuilder();
        sb.append("{farolaNumero:");
        sb.append(sc.nextLine());
        sb.append(",valor:");
        sb.append(sc.nextLine());
        sb.append("}");

        Socket s = new Socket("localhost",3333);

        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        dout.writeUTF(String.valueOf(sb));


        DataInputStream din=new DataInputStream(s.getInputStream());
        String str=din.readUTF();
        System.out.println("Servidor responde: "+str);

        dout.close();
        din.close();
        s.close();

    }


    public static void main(String[] args) throws Exception {

        sendMessage();

    }
}
