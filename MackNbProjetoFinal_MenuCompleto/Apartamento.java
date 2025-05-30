public class Apartamento extends Propriedade {
    private int andar;
    private double taxa;

    public Apartamento(String titulo, String descricao, String localizacao, int capacidade,
                       double precoPorNoite, Proprietario proprietario, int andar, double taxa) {
        super(titulo, descricao, localizacao, capacidade, precoPorNoite, proprietario);
        this.andar = andar;
        this.taxa = taxa;
    }

    public double calcularPrecoTotal(int dias) {
        return precoPorNoite * dias + taxa;
    }

    public void imprimirDados() {
        System.out.println("Apartamento: " + titulo + " - Andar: " + andar + " - " + descricao + " - R$" + precoPorNoite + "/noite");
    }
}


