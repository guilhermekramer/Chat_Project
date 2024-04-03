package org.example;


import java.io.*;
import java.net.*;
import java.util.*;



public class Servidor {
    private static final int PORTA = 6666;
    private static List<PrintWriter> clientWriters = new ArrayList<>();
    private static List<String> clientNames = new ArrayList<>();


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor rodando na porta: " + PORTA);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                outToClient.println("Bem-vindo ao chat! Você está conectado ao servidor.");

                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String nomeCliente = inFromClient.readLine();

                clientWriters.add(outToClient);
                clientNames.add(nomeCliente);

                new Thread(new ClientHandler(clientSocket, nomeCliente)).start();
                System.out.println("MAis um entrou no servidor");
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

    public static void broadcastMessage(String nomeCliente, String message) {
        String mensagemComNome = "[" + nomeCliente + "]: " + message;
        System.out.println(mensagemComNome);

        for (PrintWriter writer : clientWriters) {
            writer.println(mensagemComNome);
        }
    }

}
