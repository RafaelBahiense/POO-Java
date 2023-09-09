package ExercicioAula02;

public class Empregado {
    public int Matricula;
    public String Nome;
    
    public Empregado(int matricula, String nome) {
        Matricula = matricula;
        Nome = nome;
    }

    @Override
    public String toString() {
        return "Empregado{" +
                "Matricula=" + Matricula +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}
