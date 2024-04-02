package org.example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private String name;
    private static final int SERVER_PORT = 6666;
    private Socket socket;
    private DataOutputStream outputStream;
    public Client( String name) {
        this.name = name;
    }

    public void conectToServer() {
        try{
            Socket socket = new Socket( "localhost", SERVER_PORT);
            System.out.println(  " Nome: " + name + " Se conectou no servidor, visse.");
            outputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e + "Erro na criação do sockket do cliente");
        }
    }

    public void enviarMensagem(String message) {
        try {
            outputStream.writeUTF(name + ": " + message);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void main (String[] args){
        Client cliente1 = new Client(  "Taniro" );
        Client cliente2 = new Client( "Renato");

        cliente1.conectToServer();
//        cliente2.conectToServer();

        Scanner scanner = new Scanner(System.in);
        boolean cont = false;


        while(cont == false){
            System.out.println("Digite a mensagem: ");
            String mensagem = scanner.nextLine();

            cliente1.enviarMensagem(mensagem);


            System.out.println("Deseja digitar outra mensagem  S/N ?: ");
            if(scanner.nextLine().equals("n")){
                scanner.close();
                cont = true;
            }
        }






    }
}
