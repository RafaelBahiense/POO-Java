package fitsystem.pages;


import fitsystem.Calculator;
import fitsystem.GridBagConstraintsFactory;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ResultPagePanel extends JPanel  {
    private LayoutManager LayoutManager;
    
    private JLabel title;
    private JLabel imcResult;
    private JLabel imcClassification;
    private JLabel table;
    
    private JButton goToHomePageButton;
    private Runnable goToHomePageButtonActionPerformaded;
    
    private JButton goToCalcPageButton;
    private Runnable goToCalcPageButtonActionPerformaded;
    
    
    public ResultPagePanel(Runnable goToHomePageButtonActionPerformaded, Runnable goToCalcPageButtonActionPerformaded) {
        this.goToHomePageButtonActionPerformaded = goToHomePageButtonActionPerformaded;
        this.goToCalcPageButtonActionPerformaded = goToCalcPageButtonActionPerformaded;
        InitComponents();
    }
    
    private void InitComponents() {
        LayoutManager = new GridBagLayout();
        
        title = new JLabel("Resultado");
        title.setFont(new Font("Segoe UI", 0, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        
        imcResult = new JLabel();
        imcClassification = new JLabel();
      
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
        
        goToHomePageButton = new JButton("Ir para Home");
        goToHomePageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToHomePageButtonActionPerformaded.run();
            }
        });
        
        goToCalcPageButton = new JButton("Ir para Calculadora");
        goToCalcPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToCalcPageButtonActionPerformaded.run();
            }
        });
        
        setLayout(LayoutManager);
        add(title, GridBagConstraintsFactory.createGbc(0, 0));
        add(imcResult, GridBagConstraintsFactory.createGbc(0, 1));
        add(imcClassification, GridBagConstraintsFactory.createGbc(0, 2));
        add(table, GridBagConstraintsFactory.createGbc(0, 3));
        add(goToHomePageButton, GridBagConstraintsFactory.createGbc(0, 4, 0, 0, GridBagConstraints.NONE));
        add(goToCalcPageButton, GridBagConstraintsFactory.createGbc(1, 4, 0, 0, GridBagConstraints.NONE));
    }
    
    public void ReRender() {
        imcResult.setText(Calculator.getName() + " seu IMC é: " + Calculator.getIMC());
        imcClassification.setText("Classificação: " + Calculator.getIMCClassification());
    }
}
