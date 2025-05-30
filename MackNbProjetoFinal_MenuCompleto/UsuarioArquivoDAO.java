import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioArquivoDAO implements UsuarioDAO {
    private static final String ARQUIVO = "usuarios.txt";

    public void salvar(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(usuario.getClass().getSimpleName() + ";" + usuario.getNome() + ";" + usuario.getEmail() + ";" + usuario.getSenha());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals("Cliente")) {
                    usuarios.add(new Cliente(dados[1], dados[2], dados[3]));
                } else if (dados[0].equals("Proprietario")) {
                    usuarios.add(new Proprietario(dados[1], dados[2], dados[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}