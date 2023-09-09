package ExercicioAula03_1;


public final class Pessoa {

    private String Nome;
    private int Idade;
    private int Peso;
    
    public Pessoa(String Nome, int Idade, int Peso) {
        this.Nome = Nome;
        this.Idade = Idade;
        this.Peso = Peso;
    }

    public void Andar() {
        System.out.println(Nome + " andou um passo");
    }
    
    public void Dormir() {
        System.out.println(Nome + " foi dormir");
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getIdade() {
        return Idade;
    }

    public void setIdade(int Idade) {
        this.Idade = Idade;
    }

    public int getPeso() {
        return Peso;
    }

    public void setPeso(int Peso) {
        this.Peso = Peso;
    }

}
