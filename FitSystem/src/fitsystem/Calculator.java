package fitsystem;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    public static BigDecimal getIMC(double height, double weight) {
        var result = BigDecimal.valueOf(weight/(height*2));

        result = result.setScale(2, RoundingMode.CEILING);

        return result;
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
