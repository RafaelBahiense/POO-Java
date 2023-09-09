package exercicio.aula.pkg05;


public class ExercicioAula05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         var cliente = new Conta();
         
         cliente.setNome("Rafael");
         cliente.setAgencia(1);
         cliente.setConta(123456);
         cliente.setSaldo(1000.65);
         
         System.out.println(cliente);
    }
    
}
