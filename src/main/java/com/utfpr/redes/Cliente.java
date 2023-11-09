package com.utfpr.redes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private static Socket conexao;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Conectar ao servidor
            conexao = new Socket("127.0.0.1", 50000);

            // Informar ao usuário para digitar o CPF
            System.out.println("Por favor, digite o CPF e pressione Enter:");
            
            // Enviar CPF
            saida = new DataOutputStream(conexao.getOutputStream());
            String cpf = scanner.nextLine();
            saida.writeUTF(cpf);

            // Receber resposta do servidor
            entrada = new DataInputStream(conexao.getInputStream());
            String resposta = entrada.readUTF();
            System.out.println("Resposta do servidor: " + resposta);

            // Fechar conexão
            conexao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

