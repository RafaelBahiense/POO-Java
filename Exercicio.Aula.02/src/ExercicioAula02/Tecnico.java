package ExercicioAula02;

public class Tecnico extends Empregado {

    public String Titulo;

    public Tecnico(int matricula, String nome, String titulo) {
        super(matricula, nome);
        Titulo = titulo;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "Titulo='" + Titulo + '\'' +
                ", Matricula=" + Matricula +
                ", Nome='" + Nome + '\'' +
                '}';
    }
}
