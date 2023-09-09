package exercicio.aula.pkg05;


public class Conta {
    
    private String Nome;
    private int Agencia;
    private long Conta;
    private double Saldo;

    @Override
    public String toString() {
        return "Dados do cliente:" + "\nNome: " + Nome + "\nAgencia: "  + Agencia + "\nConta: " + Conta + "\nSaldo: " + String.format("%.2f",Saldo);
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getAgencia() {
        return Agencia;
    }

    public void setAgencia(int Agencia) {
        this.Agencia = Agencia;
    }

    public long getConta() {
        return Conta;
    }

    public void setConta(long Conta) {
        this.Conta = Conta;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }
    
}
