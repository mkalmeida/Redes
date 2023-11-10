/**
 * 
 *  @author Michaella Almeida
 * 
 */

package local.javaredes;

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

        //especificar uma porta e aguardar conexao
        try {
            servidor = new ServerSocket(50000);
        
        conexao = servidor.accept();
        System.out.println("Cliente conectou");

        //receber dados cliente
        String cpf;
        InputStream input = conexao.getInputStream();
        entrada = new DataInputStream(input);
        cpf = entrada.readUTF();

        //realizar a verificação do valor
        String resultado = "";
        if (cpf.length() != 11) {
            resultado = "CPF inválido"; // CPF deve ter 11 dígitos
        } else {
    
        int[] cpfArray = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfArray[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }
    
        int soma1 = 0;
        int multiplicador1 = 2;
        for (int i = 8; i >= 0; i--) {
            soma1 += cpfArray[i] * multiplicador1;
            multiplicador1++;
        }
        int primeiroDigito = (soma1 % 11 < 2) ? 0 : 11 - (soma1 % 11);
    
        int soma2 = 0;
        int multiplicador2 = 2;
        for (int i = 9; i >= 0; i--) {
            soma2 += cpfArray[i] * multiplicador2;
            multiplicador2++;
        }
        int segundoDigito = (soma2 % 11 < 2) ? 0 : 11 - (soma2 % 11);
    
        cpfArray[9] = primeiroDigito;
        cpfArray[10] = segundoDigito;

        if (primeiroDigito == cpf.charAt(9) && segundoDigito == cpf.charAt(10)){
            resultado = "cpf válido";
        } else{
             resultado ="cpf inválido";
        }
    }

        //Retornar dados ao cliente
        saida = new DataOutputStream(conexao.getOutputStream());
        saida.writeUTF(resultado);

        //fechar conexão
        conexao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
     }
 }