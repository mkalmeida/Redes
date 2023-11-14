import java.io.IOException;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    
    private static Socket conexao;
    private static ObjectOutputStream saida;
    private static DataInputStream entrada;
    
    public static void main(String[] args) {

        try {
            conexao = new Socket("127.0.0.1", 55000);
            Pessoa p = new Pessoa();
            p.setNome("Ana");
            p.setIdade(12);

            saida = new ObjectOutputStream(conexao.getOutputStream());
            saida.writeObject(p);

            entrada = new DataInputStream(conexao.getInputStream());
            String resposta = entrada.readUTF();
            System.out.println("Resposta do Servidor "+ resposta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
