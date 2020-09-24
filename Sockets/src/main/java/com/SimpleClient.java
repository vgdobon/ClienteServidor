package com;


import java.io.*;
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
        String string ="hola";


        dout.writeUTF(String.valueOf(sb));


        DataInputStream din=new DataInputStream(s.getInputStream());
        String str=din.readUTF();
        System.out.println("Servidor responde: "+str);

        dout.close();
        din.close();
        s.close();

    }

    public static void getDownload(){

        Scanner sc=new Scanner(System.in);

        System.out.println("Elije de que servidor quieres realizar la descarga:\n");
        System.out.println("Mirrors\n" +
                "Ftp\n" +
                "ZA\n");
        String url = sc.nextLine().toLowerCase();


        try {

            Socket socket = new Socket("localhost",3333);
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(url);

            DataInputStream dis=new DataInputStream(socket.getInputStream());
            FileOutputStream fos = new FileOutputStream("Descargas/" + url + ".deb");
            fos.write(dis.readAllBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    public static void main(String[] args) throws Exception {

        //sendMessage();
        getDownload();
    }
}
