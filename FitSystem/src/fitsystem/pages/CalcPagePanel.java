package fitsystem.pages;


import fitsystem.Calculator;
import fitsystem.GridBagConstraintsFactory;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CalcPagePanel extends JPanel {
    
    private LayoutManager layoutManager;
    
    private JLabel title;
    
    private JLabel name;
    private JTextField nameInput;
    
    private JLabel height;
    private JTextField heightInput;
    
    private JLabel weight;
    private JTextField weightInput;
    
    private JButton goToResultPageButton;
    private Runnable goToResultPageButtonActionPerformaded;
    
    
    public CalcPagePanel(Runnable goToResultPageButtonActionPerformaded) {
        this.goToResultPageButtonActionPerformaded = goToResultPageButtonActionPerformaded;
        InitComponents();
    }
    
    private void InitComponents() {
        layoutManager = new GridBagLayout();
        
        title = new JLabel("Preencha seus dados");
        title.setFont(new Font("Segoe UI", 0, 24));
        title.setHorizontalAlignment(JLabel.CENTER);
        
        name = new JLabel("Nome: ");
        nameInput = new JTextField();
        
        height = new JLabel("Altura:");
        heightInput = new JTextField();
        
        weight = new JLabel("Peso:  ");
        weightInput = new JTextField();
        
        goToResultPageButton = new JButton("Calcular");
        goToResultPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var nameValue = nameInput.getText();
                Calculator.setName(nameValue);
                
                if(nameValue.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Nome inválido");
                    Calculator.Clear();
                    return;
                }
                
                var heightValue = heightInput.getText();
                try {
                    Calculator.setHeight(Double.parseDouble(heightValue));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Altura inválida");
                    Calculator.Clear();
                    return;
                }
                
                if(Calculator.getHeight() < 1) {
                    JOptionPane.showMessageDialog(null, "Altura inválida");
                    Calculator.Clear();
                    return;
                }
                
                var weightValue = weightInput.getText();
                try {
                    Calculator.setWeight(Double.parseDouble(weightValue));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Peso inválido");
                    Calculator.Clear();
                    return;
                }
                
                if(Calculator.getWeight() < 1) {
                    JOptionPane.showMessageDialog(null, "Peso inválido");
                    Calculator.Clear();
                    return;
                }
                
                nameInput.setText("");
                heightInput.setText("");
                weightInput.setText("");
                
                goToResultPageButtonActionPerformaded.run();
            }
        });
        goToResultPageButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        setLayout(layoutManager);
        
        add(title, GridBagConstraintsFactory.createGbc(0, 0));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 2; 
        gbc.weightx = 1.0; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.gridwidth = 1; 
        gbc.gridy = 1;

        nameInput.setPreferredSize(new Dimension(200, 30));
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(name);
        namePanel.add(nameInput);
        add(namePanel, gbc);

        gbc.gridy = 2;
        heightInput.setPreferredSize(new Dimension(200, 30));
        JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        heightPanel.add(height);
        heightPanel.add(heightInput);
        add(heightPanel, gbc);

        gbc.gridy = 3;
        weightInput.setPreferredSize(new Dimension(200, 30));
        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        weightPanel.add(weight);
        weightPanel.add(weightInput);
        add(weightPanel, gbc);

        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        goToResultPageButton.setPreferredSize(new Dimension(150, 30));
        add(goToResultPageButton, gbc);
    }
    
}
