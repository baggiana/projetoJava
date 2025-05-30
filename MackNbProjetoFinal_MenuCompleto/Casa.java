public class Casa extends Propriedade {
    boolean possuiPiscina;
    private double precoPorPessoa;

    public Casa(String titulo, String descricao, String localizacao, int capacidade,
                double precoPorNoite, Proprietario proprietario, boolean possuiPiscina, double precoPorPessoa) {
        super(titulo, descricao, localizacao, capacidade, precoPorNoite, proprietario);
        this.possuiPiscina = possuiPiscina;
        this.precoPorPessoa = precoPorPessoa;
    }

    public double calcularPrecoTotal(int dias) {
        return precoPorPessoa * precoPorNoite * dias;
    }

    public void imprimirDados() {
        System.out.println("Casa: " + titulo + " - " + descricao + " - " + localizacao + " - R$" + precoPorNoite + "/noite");
    }
}