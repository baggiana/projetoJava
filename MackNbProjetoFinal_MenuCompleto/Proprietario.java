import java.util.ArrayList;

public class Proprietario extends Usuario {
    private ArrayList<Propriedade> propriedades = new ArrayList<>();

    public Proprietario(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void cadastrarPropriedade(Propriedade p) {
        propriedades.add(p);
    }

    public void listarPropriedades() {
        for (Propriedade p : propriedades) {
            p.imprimirDados();
        }
    }

    public void listarPropriedadesAlugadas() {
        for (Propriedade p : propriedades) {
            if (!p.estaDisponivel()) {
                p.imprimirDados();
            }
        }
    }
@Override
    public void imprimirDados() {
        System.out.println("Proprietário: " + nome + ", Email: " + email);
    }
}