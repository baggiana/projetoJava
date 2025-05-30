public abstract class Propriedade {
    protected boolean disponivel;
    protected String titulo;
    protected String descricao;
    protected String localizacao;
    protected int capacidade;
    protected double precoPorNoite;
    protected Proprietario proprietario;

    public Propriedade(String titulo, String descricao, String localizacao,
                       int capacidade, double precoPorNoite, Proprietario proprietario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
        this.precoPorNoite = precoPorNoite;
        this.proprietario = proprietario;
        this.disponivel = true;
    }

    public boolean estaDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getPrecoPorNoite() {
        return precoPorNoite;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public abstract double calcularPrecoTotal(int dias);
    public abstract void imprimirDados();
}
