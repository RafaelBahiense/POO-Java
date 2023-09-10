package imc.calculator;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class HomePagePanel extends JPanel {
    
    private GridBagLayout layoutManager;
    
    private JLabel title;
    private JLabel paragraph;
    private JLabel table;
    
    private JButton goToCalcPageButton;
    private Runnable goToCaclPageButtonActionPerformaded;
    
    public HomePagePanel(Runnable goToCaclPageButtonActionPerformaded) {
        this.goToCaclPageButtonActionPerformaded = goToCaclPageButtonActionPerformaded;
        InitComponents();
    }

    private void InitComponents() {
        layoutManager = new GridBagLayout();
        
        title = new JLabel("Calculadora do IMC");
        title.setFont(new Font("Segoe UI", 0, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
            
        paragraph = new JLabel("<html>"
                + "<p>O que é IMC</p>"
                + "<br/>"
                + "<p style=\\\"width:100%\\\">Criado no século 19 pelo matemático Lambert Quételet,"
                + " o Índice de Massa Corporal,<br/> conhecido pela sigla IMC, é um cálculo simples"
                + " que permite medir se alguém está ou não com o peso ideal.<br/> Ele aponta se o"
                + " peso está adequado ou se está abaixo ou acima do peso.</p>"
                + "</html>");
        table = new JLabel("<html>"
                + "<p>IMC - Classificação do IMC</p>"
                + "<br/><p>Menor que 16 - Magreza grave</p>"
                + "<p>16 a menor que 17 - Magreza moderada</p>"
                + "<p>17 a menor que 18,5 - Magreza leve</p>"
                + "<p>18,5 a menor que 25 - Saudável</p>"
                + "<p>25 a menor que 30 - Sobrepeso</p>"
                + "<p>30 a menor que 35 - Obesidade Grau I</p>"
                + "<p>35 a menor que 40 - Obesidade Grau II (considerada severa)</p>"
                + "<p>Maior que 40 - Obesidade Grau III (considerada mórbida)</p>"
                + "</html>");
        
        goToCalcPageButton = new JButton("Ir para calculadora");
        goToCalcPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToCaclPageButtonActionPerformaded.run();
            }
        });
        
        setLayout(layoutManager);
        add(title, GridBagConstraintsFactory.createGbc(0, 0));
        add(paragraph, GridBagConstraintsFactory.createGbc(0, 1));
        add(table, GridBagConstraintsFactory.createGbc(0, 2));
        add(goToCalcPageButton, GridBagConstraintsFactory.createGbc(0, 3, 0, 0, GridBagConstraints.NONE));
    }
}
