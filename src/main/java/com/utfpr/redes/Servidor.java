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
	private static DataInputStream entrada;
	private static DataOutputStream saida;

	public static void main(String[] args) {

		try {
			//especificar porta e aguardar conexão
			servidor = new ServerSocket(50000);
			conexao = servidor.accept();
			System.out.println("Cliente conectou");

			//receber dados do cliente
			String cpf;

			InputStream input = conexao.getInputStream();
			BufferedReader entrada = new BufferedReader(new InputStreamReader(input));
            cpf = entrada.readLine();
			
			//realiza verificação do cpf

			//transformando cpf em array para poder percorrer os números
			char[] cpfArray = cpf.toCharArray();
			int soma=0;
			double div=0.0, modulo =0.0;
			int multiplicador=2, multiplic=2;
			int penultimoNumero=0, ultimoNumero=0;
			//pegando o último número
			if (cpfArray.length == 10){
	
				for (int i=8; i>0; i--){
					soma=soma + cpfArray[i]*multiplicador;
					multiplicador++;
				}

				div = (soma/11);

				if((int)div == 0 || (int)div == 1){
					cpfArray[9] = 0;
				} else {
					modulo = (soma%11)*11;
					int moduloArredondado = (int) Math.round(modulo);
					penultimoNumero = 11 - moduloArredondado;
				}

				StringBuilder dezDigitos = new StringBuilder();

				for (int i=0; i < 8; i++){
					dezDigitos.append(cpfArray[i]);
				}
				dezDigitos.append(penultimoNumero);

				for (int i=9; i>0; i--){
					soma=soma + dezDigitos.charAt(i);
					multiplic++;
				}
				
				div = (soma/11);

				if((int)div == 0 || (int)div == 1){
					cpfArray[9] = 0;
				} else {
					modulo = (soma%11)*11;
					int moduloArredondado = (int) Math.round(modulo);
					ultimoNumero = 11 - moduloArredondado;
				}

				if(penultimoNumero >=0 && ultimoNumero >=0){
					System.out.println("CPF válido");
				} else {
					System.out.println("CPF inválido");
				}
				


			} else {
				System.out.println("CPF inválido");
			}
			
			//retorna dados do cliente
			saida = new DataOutputStream((conexao.getOutputStream()));
			saida.writeUTF(cpf);
	
			//fecha conexão
			conexao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
