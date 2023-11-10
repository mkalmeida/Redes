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
        int valor;
        entrada = new DataInputStream(conexao.getInputStream());
        valor = entrada.readInt();

        //realizar a verificação do valor
        String resultado = "";
        if (valor>0)
            resultado = "O valor é maior que 0";
        else
            resultado = "o valor é menor ou igual a zero";

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