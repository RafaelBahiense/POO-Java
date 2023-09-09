package ExercicioAula03_1;

import java.util.Scanner;


public class ExercicioAula03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var input = new Scanner(System.in);
        var pessoas = new Pessoa[5];
        
        System.out.println("Vamos preencher as pessoas: \n");
        
        for (var i = 0; i < pessoas.length; i++) {
            var counter = String.valueOf(i + 1);
            System.out.println("Pessoa ".concat(counter));
            
            System.out.print("\nNome: ");
            var nome = input.next();
            
            System.out.print("Idade: ");
            var idade = input.nextInt();
            
            System.out.print("Peso: ");
            var peso = input.nextInt();
            
            var conta = new Pessoa(nome, idade, peso);
            
            pessoas[i] = conta;
            
            System.out.println();
        }
        
        System.out.println("O nome das criancas sao: \n");
        
        for(var pessoa: pessoas) {
            if(pessoa.getIdade() < 12) {
                System.out.println(pessoa.getNome());
            }
        } 
    }
    
}
