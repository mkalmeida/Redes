import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.lang.System.Logger;

public class Servidor{

    
    private static Socket conexao;
    private static ServerSocket servidor;
    private static ObjectInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String args[]){

        try{
        servidor = new ServerSocket(55000);
        System.out.println("Aguardando conexão....");
        conexao = servidor.accept();

        entrada = new ObjectInputStream(conexao.getInputStream());
        Pessoa pessoa = (Pessoa) entrada.readObject();
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Idade: " + pessoa.getIdade());

        saida = new DataOutputStream(conexao.getOutputStream());
        saida.writeUTF("Objeto Recebido");

        conexao.close();

        } catch(IOException ex){
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}