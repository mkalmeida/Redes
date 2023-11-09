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

    public static void main (String args[]) {
        Scanner scanner = new Scanner(System.in);
        try {
        // conectar ao servidor
        conexao = new Socket("127.0.0.1", 50000);

        // enviar cpf
        saida = new DataOutputStream(conexao.getOutputStream());
        String cpf = scanner.nextLine();
        saida.writeUTF(cpf);

        // receber resposta do servidor
        entrada = new DataInputStream(conexao.getInputStream());
        String resposta = entrada.readUTF();
        System.out.println("Resposta do servidor: " + resposta);
        // fechar conexao
        conexao.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
