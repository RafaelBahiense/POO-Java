package imc.calculator;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


public class MainFrame extends JFrame {
    
    private Border border;
    private CardLayout cardLayout;
    
    private JPanel panelContainer;
    private HomePagePanel homePanel;
    private CalcPagePanel calcPanel;
    private ResultPagePanel resultPanel;


    public MainFrame() {
        InitComponents();
        SetupFrame();
    }
    
    private void SetupFrame() {
        add(panelContainer, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("Calculadora do IMC");
        setMaximumSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(500, 500));
        pack();
        setVisible(true);
    }

    private void InitComponents() {
        panelContainer = new JPanel(cardLayout);
        
        border = BorderFactory.createEmptyBorder(15, 15, 15, 15);   
        cardLayout = new CardLayout();
        
        panelContainer.setBorder(border);
        panelContainer.setLayout(cardLayout);
        
        Runnable goToHomePagePanel = () -> cardLayout.show(panelContainer, "Home Page");
        Runnable goToCalcPagePanel = () -> cardLayout.show(panelContainer, "Calc Page");
        Runnable goToResultPagePanel = () -> {
            resultPanel.ReRender();
            cardLayout.show(panelContainer, "Result Page");
        };
        
        homePanel = new HomePagePanel(goToCalcPagePanel);
        calcPanel = new CalcPagePanel(goToResultPagePanel);
        resultPanel = new ResultPagePanel(goToHomePagePanel, goToCalcPagePanel);
        
        panelContainer.add(homePanel, "Home Page");
        panelContainer.add(calcPanel, "Calc Page");
        panelContainer.add(resultPanel, "Result Page");
    }

    public void goToCaclPage() {
        cardLayout.show(panelContainer, "Calc Page");
    }
    
    public static void main(String[] args) {
        new MainFrame();
    }
    
}
