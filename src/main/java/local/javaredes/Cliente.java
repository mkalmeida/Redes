/**
 * 
 *  @author Michaella Almeida
 * 
 */

package local.javaredes;

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

        try {
            // Connect to the server
            conexao = new Socket("127.0.0.1", 50000);

            // Get user input from the terminal
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite um número: ");
            String cpf = scanner.nextLine();

            // Send the number to the server
            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(cpf);

            // Receive the server's response
            entrada = new DataInputStream(conexao.getInputStream());
            String resposta = entrada.readUTF();
            System.out.println("Resposta do servidor: " + resposta);

            // Close the connection
            conexao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


