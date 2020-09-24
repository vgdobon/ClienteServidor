package com;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import static com.Checker.check;

public class SimpleServer {

    public static void start() {
        ServerSocket ss= null;
        try {
            ss = new ServerSocket(3333);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Escuchando peticiones...");
        Socket s= null;
        try {
            s = ss.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Petición escuchada");
        DescargaFichero descargaFichero = new DescargaFichero();

        DataInputStream din= null;
        try {
            din = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String str= null;
        try {
            str = din.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String resultado=check(str);

        System.out.println("Client says: "+str);

        DataOutputStream dout = null;
        try {
            dout = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(resultado=="OK"){
            try {
                dout.writeUTF(resultado+" -> Datos correctos");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (resultado=="KO"){
            try {
                dout.writeUTF(resultado+" -> Datos incorrectos");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            din.close();
            dout.close();
            s.close();
            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void comenzarDescarga() {

        ServerSocket serverSocket= null;
        DataInputStream din=null;
        DataOutputStream dout=null;
        Socket socket = null;
        DescargaFichero descargaFichero = new DescargaFichero();


        try {
            serverSocket = new ServerSocket(3333);
            System.out.println("Escuchando peticiones...");
            socket=serverSocket.accept();
            System.out.println("Petición escuchada");

            din=new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            String url=din.readUTF();
            descargaFichero.descargarFichero(url,dout);



        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            din.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public static void main(String[] args) throws Exception {

//        start();
        comenzarDescarga();
    }
}
