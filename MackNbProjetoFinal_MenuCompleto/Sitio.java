public class Sitio extends Propriedade {
    private double areaTotal;

    public Sitio(String titulo, String descricao, String localizacao, int capacidade,
                 double precoPorNoite, Proprietario proprietario, double areaTotal) {
        super(titulo, descricao, localizacao, capacidade, precoPorNoite, proprietario);
        this.areaTotal = areaTotal;
    }

    public double calcularPrecoTotal(int dias) {
        return precoPorNoite * dias;
    }

    public void imprimirDados() {
        System.out.println("Sítio: " + titulo + " - " + areaTotal + "m² - " + descricao + " - R$" + precoPorNoite + "/noite");
    }
}