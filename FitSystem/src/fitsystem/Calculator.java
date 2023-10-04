package fitsystem;


public class Calculator {
    
    private static String name;
    private static  double height;
    private static double weight;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Calculator.name = name;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        Calculator.height = height;
    }

    public static double getWeight() {
        return weight;
    }

    public static void setWeight(double weight) {
        Calculator.weight = weight;
    }
    
    public static double getIMC() {
        return weight/(height*2);
    }
    
    public static String getIMCClassification() {
        var result = getIMC();
        
        if(result < 16) {
            return "Magreza grave";
        }
        
        if(result < 17) {
            return "Magreza moderada";
        }
        
        if(result < 18.5) {
            return "Magreza leve";
        }
        
        if(result < 25) {
            return "Saudável";
        }
        
        if(result < 30) {
            return "Sobrepeso";
        }
        
        if(result < 35) {
            return "Obesidade Grau I";
        }
        
        if(result < 40) {
            return "Obesidade Grau II";
        }
        
        return "Obesidade Grau III";
    }
    
    public static void Clear() {
        name = "";
        height = 0;
        weight = 0;
    }
    
}
