package exercicio.aula.pkg07;


public class ExercicioAula07 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var myThread = new Thread(new Mostra() );
        var myThread2 = new Thread(new Mostra());
    }
    
}
