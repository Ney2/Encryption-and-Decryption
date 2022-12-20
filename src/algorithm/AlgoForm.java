/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import  algorithm.TDES;
import algorithm.AES;
import algorithm.OTP;
/**
 *
 * @author userpc
 */
public class AlgoForm  extends JFrame {

    /**
     * @param args the command line arguments
     */
   
    private JTextArea encryptbox = new JTextArea(4, 18);
    private JTextArea decryptbox = new JTextArea(4,18);
    private JTextArea openc = new JTextArea(4,18);
    private JTextArea opdec = new JTextArea(4,18);
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JButton encrypt = new JButton("ENCRYPT");
    private JButton decrypt = new JButton("DECRYPT");
    String[] choose =  {"OTP", "TDES", "AES"};
    private JComboBox choice = new JComboBox<>(choose);
    private static int algorithm = 0;
    TDES tripleDES = new TDES();
    AES aes = new AES();
   
    private String pad;
    private String secretKey = "SecretKey";

    public AlgoForm(){
        setTitle("Encyption and Decryption");
        setVisible(true);
        setResizable( false );



        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        encryptbox.setForeground(Color.BLUE);
        decryptbox.setForeground(Color.BLUE);
        openc.setForeground(Color.BLUE);
        opdec.setForeground(Color.BLUE);
        decryptbox.setFont(new Font("Arial",Font.BOLD,16));
        encryptbox.setFont(new Font("Arail",Font.BOLD,16));
        opdec.setFont(new Font("Arial",Font.BOLD,16));
        openc.setFont(new Font("Arial",Font.BOLD,16));
        encryptbox.setLineWrap(true);
        decryptbox.setLineWrap(true);
        openc.setLineWrap(true);
        opdec.setLineWrap(true);
        
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        
        panel1.add(encryptbox); 
        panel2.add(decryptbox); 
        panel1.add(encrypt); 
        panel2.add(decrypt); 
        panel1.add(openc); 
        panel2.add(opdec); 
        panel3.add(choice); 
        
        panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel1.setPreferredSize(new Dimension(400, 200));
        panel1.setMaximumSize(new Dimension(400, 200));
        panel1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panel2.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel2.setPreferredSize(new Dimension(400, 200));
        panel2.setMaximumSize(new Dimension(400, 200));
        panel2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        panel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.setPreferredSize(new Dimension(800, 60));
        panel3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
                
        c.add(panel3, BorderLayout.NORTH);
        c.add(panel1, BorderLayout.WEST);
        c.add(panel2, BorderLayout.EAST);

        choice.addActionListener(new EL());
        encrypt.addActionListener(new EL());
        decrypt.addActionListener(new EL());

    }

    private class EL implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource() == choice){
                algorithm = choice.getSelectedIndex();
                System.out.print(algorithm);
            }
            if (e.getSource() == encrypt){
                switch(algorithm){
                    case 0:
                        pad = OTP.generate_pad(encryptbox.getText().length());
                        String ciphertext = OTP.encrypt(encryptbox.getText(), pad);
                        openc.setText(ciphertext);
                        break;
                    case 1:
                        String clearText = encryptbox.getText();
                        String data = tripleDES.encrypt(clearText, secretKey);
                        openc.setText(data);
                        System.out.print(clearText);                   
                        break;
                    case 2:
                        try {
                            aes.init();
                            String encryptedMessage = aes.encrypt(encryptbox.getText());
                            openc.setText(encryptedMessage);
                        } catch (Exception ignored) {
                            }
                        break;
                }
            }
        
            if (e.getSource() == decrypt){
                switch(algorithm){
                    case 0:
                    
                        String plaintextOTP = OTP.decrypt(pad, decryptbox.getText());
                        opdec.setText(plaintextOTP);
                        break;
                    case 1:
                        
                        String plaintextDES = tripleDES.decrypt(decryptbox.getText(), secretKey);
                        opdec.setText(plaintextDES);               
                        break;
                    case 2:
                        try {
                            String decryptedMessage = aes.decrypt(decryptbox.getText());
                            opdec.setText(decryptedMessage);
                        } catch (Exception ignored) {
                            }
                        break;
                }
            }
        }
         public static void main(String[] args) {
new AlgoForm();
    
}
    }
}
   

    
        
