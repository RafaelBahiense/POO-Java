package exercicio.aula.pkg04;

import javax.swing.*;


public class ExercicioAula04 extends JFrame {
    
    private JPanel panel;
    private JButton button;
    private JLabel label;
    private JTextField textField;
    private String userFullName;

    public ExercicioAula04() {
        initComponents();
    }
    
    private void initComponents() {
        
        setSize(300, 100);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        panel = new JPanel();
        getContentPane().add(panel);
        
        label = new JLabel("Nome Completo");
        textField = new JTextField("Digite seu Nome");
        button = new JButton("Salvar");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        
        panel.add(label);
        panel.add(textField);
        panel.add(button);    
     }
     
     private void btnActionPerformed(java.awt.event.ActionEvent evt) {                                        
        this.dispose();
        userFullName = textField.getText();
        JOptionPane.showMessageDialog(null, "Usuário cadastrado: " + userFullName); 
    }
     
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExercicioAula04().setVisible(true);
            }
        });
    }
}
