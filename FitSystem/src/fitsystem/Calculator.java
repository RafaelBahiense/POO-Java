package fitsystem;


public class Calculator {

    public static double getIMC(double height, double weight) {
        return weight/(height*2);
    }
    
    public static String getIMCClassification(double imc) {
        
        if(imc < 16) {
            return "Magreza grave";
        }
        
        if(imc < 17) {
            return "Magreza moderada";
        }
        
        if(imc < 18.5) {
            return "Magreza leve";
        }
        
        if(imc < 25) {
            return "Saudável";
        }
        
        if(imc < 30) {
            return "Sobrepeso";
        }
        
        if(imc < 35) {
            return "Obesidade Grau I";
        }
        
        if(imc < 40) {
            return "Obesidade Grau II";
        }
        
        return "Obesidade Grau III";
    }
}
