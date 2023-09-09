package ExercicioAula02;

import javax.swing.JOptionPane;


public class ExercicioAula02 {
    public static void MostrarTecnico(Tecnico tecnico) {
         JOptionPane.showMessageDialog(null, tecnico.toString(), "Dados do Técnico", JOptionPane.PLAIN_MESSAGE);
    }
    
    
    public static void main(String[] args) {        
        var matriculaInput = JOptionPane.showInputDialog("Digite a matricula: ");
        var matricula = Integer.parseInt(matriculaInput);
        
        var nome = JOptionPane.showInputDialog("Digite o nome: ");
        
        var titulo = JOptionPane.showInputDialog("Digite o Título: ");
        
        var tecnico = new Tecnico(matricula, nome, titulo);

        
        //System.out.println(tecnico);
        MostrarTecnico(tecnico);
        
    }
    
}
