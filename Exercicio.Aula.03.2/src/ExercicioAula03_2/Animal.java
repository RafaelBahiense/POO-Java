package ExercicioAula03_2;


public class Animal {
    
    private String Nome;
    private int Idade;
    private String LocalEncontrado;

    public Animal(String Nome, int Idade, String LocalEncontrado) {
        this.Nome = Nome;
        this.Idade = Idade;
        this.LocalEncontrado = LocalEncontrado;
    }
    
    public void Andar() {
        System.out.println(Nome + " andou um passo");
    }
    
    public void Comer() {
        System.out.println(Nome + " se alimentou");
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

    public String getLocalEncontrado() {
        return LocalEncontrado;
    }

    public void setLocalEncontrado(String LocalEncontrado) {
        this.LocalEncontrado = LocalEncontrado;
    }
    
    
    
    
}
