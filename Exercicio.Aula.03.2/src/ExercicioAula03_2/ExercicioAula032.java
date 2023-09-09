package ExercicioAula03_2;

import java.util.Scanner;

/**
 *
 * @author rafael.bahiense
 */
public class ExercicioAula032 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {                
        var input = new Scanner(System.in);
        var animais = new Animal[10];
        
        System.out.println("Vamos preencher os Animais: \n");
        
        for (var i = 0; i < animais.length; i++) {
            var counter = String.valueOf(i + 1);
            System.out.println("Animal ".concat(counter));
            
            System.out.print("\nNome: ");
            var nome = input.next();
            
            System.out.print("Idade: ");
            var idade = input.nextInt();
           
            System.out.print("Local Encontrado: ");
            var local = input.next();
            
            var animal = new Animal(nome, idade, local);
            
            animais[i] = animal;
            
            System.out.println();
        }
        
        System.out.print("Animais com mais de 50 anos: ");
        
        var count = 0;
        
        for(var animal: animais) {
            if(animal.getIdade() > 50) {
                count++;
            }
        }
        
        System.out.println(count);
    }
    
}
