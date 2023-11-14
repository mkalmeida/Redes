package local.javaredes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static ServerSocket servidor;
    private static Socket conexao;
    private static ObjectInputStream entrada;
    private static DataOutputStream saida;

    public static void main(String args[]) {
        try {
            servidor = new ServerSocket(55000);
            conexao = servidor.accept();

            entrada = new ObjectInputStream(conexao.getInputStream());
            Pessoa pessoa = (Pessoa) entrada.readObject();
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Idade: " + pessoa.getIdade());

            saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF("Dados Recebidos corretamente!");

            conexao.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
