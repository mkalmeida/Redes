package com.utfpr.redes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static ServerSocket servidor;
    private static Socket conexao;

    public static void main(String[] args) {
        try {
            servidor = new ServerSocket(50000);
            conexao = servidor.accept();
            System.out.println("Cliente conectou");

            String cpf;
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            cpf = entrada.readLine();

            if (verificarCPF(cpf)) {
                System.out.println("CPF válido");
            } else {
                System.out.println("CPF inválido");
            }

            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(cpf);

            conexao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificarCPF(String cpf) {
        if (cpf.length() != 11) {
            return false; // CPF deve ter 11 dígitos
        }

        int[] cpfArray = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfArray[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int primeiroDigito = calcularPrimeiroDigitoVerificador(cpfArray);
        int segundoDigito = calcularSegundoDigitoVerificador(cpfArray, primeiroDigito);

        return cpfArray[9] == primeiroDigito && cpfArray[10] == segundoDigito;
    }

    public static int calcularPrimeiroDigitoVerificador(int[] cpfArray) {
        int soma = 0;
        int multiplicador = 2;
        for (int i = 8; i >= 0; i--) {
            soma += cpfArray[i] * multiplicador;
            multiplicador++;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    public static int calcularSegundoDigitoVerificador(int[] cpfArray, int primeiroDigito) {
        int soma = 0;
        int multiplicador = 2;
        for (int i = 9; i >= 0; i--) {
            soma += cpfArray[i] * multiplicador;
            multiplicador++;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}