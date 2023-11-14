/**
 * 
 *  @author Michaella Almeida
 * 
 */

package local.javaredes;

 import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static ServerSocket servidor;
    private static Socket conexao;
    private static DataInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String[] args) {

        try {
            servidor = new ServerSocket(50000);
            conexao = servidor.accept();
            System.out.println("Cliente conectou");

            // Receber dados do cliente
            InputStream input = conexao.getInputStream();
            entrada = new DataInputStream(input);
            String cpf = entrada.readUTF();

            // Realizar a verificação do cpf
            String resultado = "";
            if (cpf.length() != 11) {
                resultado = "CPF inválido"; 
            } else {

                int[] cpfArray = new int[11];
                for (int i = 0; i < 11; i++) {
                    cpfArray[i] = Integer.parseInt(cpf.substring(i, i + 1));
                }

                int soma1 = 0;
                int multiplicador1 = 10;
                for (int i = 0; i < 9; i++) {
                    soma1 += cpfArray[i] * multiplicador1;
                    multiplicador1--;
                }
                int primeiroDigito = (soma1 % 11 < 2) ? 0 : 11 - (soma1 % 11);

                int soma2 = 0;
                int multiplicador2 = 11;
                for (int i = 0; i < 10; i++) {
                    soma2 += cpfArray[i] * multiplicador2;
                    multiplicador2--;
                }
                int segundoDigito = (soma2 % 11 < 2) ? 0 : 11 - (soma2 % 11);

                // Corrigido na verificação dos dígitos verificadores
                if (primeiroDigito == cpfArray[9] && segundoDigito == cpfArray[10]) {
                    resultado = "CPF válido";
                } else {
                    resultado = "CPF inválido";
                }
            }

            // Retornar dados ao cliente
            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(resultado);

            // Fechar conexão
            conexao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
