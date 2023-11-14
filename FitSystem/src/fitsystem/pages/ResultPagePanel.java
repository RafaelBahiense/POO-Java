package fitsystem.pages;


import fitsystem.Calculator;
import fitsystem.GridBagConstraintsFactory;
import fitsystem.State;

import java.awt.*;
import javax.swing.*;


public class ResultPagePanel extends JPanel  {

    private LayoutManager LayoutManager;
    private JLabel title;
    private JLabel imcResult;
    private JLabel imcClassification;
    private JLabel table;
    private JButton goToClientsPageButton;
    private Runnable goToClientsPageButtonActionPerformaded;
    private State state;
    
    public ResultPagePanel(Runnable goToClientsPageButtonActionPerformaded, State state) {
        this.goToClientsPageButtonActionPerformaded = goToClientsPageButtonActionPerformaded;
        this.state = state;
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
        
        goToClientsPageButton = new JButton("Ir para Lista de Clientes");
        goToClientsPageButton.addActionListener(e -> goToClientsPageButtonActionPerformaded.run());

        
        setLayout(LayoutManager);
        add(title, GridBagConstraintsFactory.createGbc(0, 0));
        add(imcResult, GridBagConstraintsFactory.createGbc(0, 1));
        add(imcClassification, GridBagConstraintsFactory.createGbc(0, 2));
        add(table, GridBagConstraintsFactory.createGbc(0, 3));
        add(goToClientsPageButton, GridBagConstraintsFactory.createGbc(0, 4, 0, 0, GridBagConstraints.NONE));
    }
    
    public void ReRender() {
        imcResult.setText(state.CurrentClient.Name + " seu IMC é: " + state.CurrentClientHealthMetrics.ImcValue.toString());
        imcClassification.setText("Classificação: " + Calculator.getIMCClassification(state.CurrentClientHealthMetrics.ImcValue.doubleValue()));
    }
}
