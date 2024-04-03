package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Erro na emissão do cliente, digite o Host e a porta");
            System.exit(1);
        }

        String hostName = args[0];
        int portaNumber = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(hostName, portaNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Digite seu nome:");
            String nome = stdIn.readLine();
            out.println(nome);


            Thread receiverThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao ler mensagem do servidor: " + e.getMessage());
                }
            });
            receiverThread.start();

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
            }
        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Não foi possível obter I/O para a conexão com " + hostName);
            System.exit(1);
        }
    }
}

