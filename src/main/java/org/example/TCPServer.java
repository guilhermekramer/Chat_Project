package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketOption;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    private static final int PORT = 6666;
    private static final List<Client> clients = new ArrayList<>();


    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor Iniciado na porta: " + PORT);

            while(true){
                Socket clientSocket = serverSocket.accept();
                if(clientSocket.isBound()){

                    Client client = new Client( clientSocket.getInetAddress().getHostName() );
                    clients.add(client);
                    System.out.println("Cliente aceito " + client);


                }

                ;
            }


        } catch (IOException e) {
            throw new RuntimeException(e + "Erro ao criar o Socket");
        }




    }
}
