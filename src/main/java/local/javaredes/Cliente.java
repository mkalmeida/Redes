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
            
            conexao = new Socket("127.0.0.1", 50000);

           
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite um número: ");
            String cpf = scanner.nextLine();

           
            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(cpf);

            
            entrada = new DataInputStream(conexao.getInputStream());
            String resposta = entrada.readUTF();
            System.out.println("Resposta do servidor: " + resposta);

           
            conexao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


