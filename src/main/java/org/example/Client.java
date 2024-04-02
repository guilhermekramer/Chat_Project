package org.example;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String name;
    private static final int SERVER_PORT = 6666;
    String message;

    public Client( String name) {
        this.name = name;
    }

    public void conectToServer() {
        try{
            Socket socket = new Socket( "localhost", SERVER_PORT);
            System.out.println(  " Nome: " + name + " Se conectou no servidor, visse.");

        } catch (IOException e) {
            throw new RuntimeException(e + "Erro na criação do sockket do cliente");
        }
    }

    public static void main (String[] args){
        Client cliente1 = new Client(  "Taniro" );

        Client cliente2 = new Client( "Renato");

        cliente1.conectToServer();
        cliente2.conectToServer();




    }
}
