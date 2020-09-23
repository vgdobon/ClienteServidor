package com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.Checker.check;

public class SimpleServer {


    public static void start() throws Exception {
        ServerSocket ss=new ServerSocket(3333);

        System.out.println("Escuchando peticiones...");
        Socket s=ss.accept();
        System.out.println("Petición escuchada");

        DataInputStream din=new DataInputStream(s.getInputStream());

        String str=din.readUTF();

        String resultado=check(str);

        System.out.println("Client says: "+str);

        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        if(resultado=="OK"){
            dout.writeUTF(resultado+" -> Datos correctos");
        }
        if (resultado=="KO"){
            dout.writeUTF(resultado+" -> Datos incorrectos");
        }


        din.close();
        dout.close();
        s.close();
        ss.close();

    }
    public static void main(String[] args) throws Exception {

        start();

    }
}
