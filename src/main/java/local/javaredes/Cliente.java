package local.javaredes;

import javax.swing.*;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    private static Socket conexao;
    private static ObjectOutputStream saida;
    private static DataInputStream entrada;
    private static JFrame frame;
    private static JTextField textField1;
    private static JTextField textField2;
    private static JTextField textField3;
    private static JButton enviarButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarInterface());
    }

    private static void criarInterface() {
        frame = new JFrame("Exercicio 2");
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        enviarButton = new JButton("Enviar");

        JLabel label1 = new JLabel("Nome");
        label1.setBounds(6, 0, 200, 30);
        textField1.setBounds(5, 24, 500, 30);
        JLabel label2 = new JLabel("Idade");
        label2.setBounds(6, 52, 200, 30);
        textField2.setBounds(5, 76, 500, 30);
        JLabel label3 = new JLabel("Retorno do Servidor");
        label3.setBounds(6, 105, 200, 30);
        textField3.setBounds(5, 130, 500, 100);
        enviarButton.setBounds(404, 240, 100, 30);

        enviarButton.addActionListener(e -> enviarParaServidor());

        frame.add(textField1);
        frame.add(textField2);
        frame.add(textField3);
        frame.add(enviarButton);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);

        frame.getContentPane().setBackground(new Color(224, 227, 233));
        frame.setSize(510, 320);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void enviarParaServidor() {
        try {
            conexao = new Socket("127.0.0.1", 55000);

            Pessoa p = new Pessoa();
            p.setNome(textField1.getText());

            try {
                int idade = Integer.parseInt(textField2.getText());
                p.setIdade(idade);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Idade inválida. Digite um número inteiro.");
                return;
            }

            saida = new ObjectOutputStream(conexao.getOutputStream());
            saida.writeObject(p);

            entrada = new DataInputStream(conexao.getInputStream());
            String resposta = entrada.readUTF();

            textField3.setText("Recebeu do Servidor: \n" + resposta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}