import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropriedadeArquivoDAO implements PropriedadeDAO {
    private static final String ARQUIVO = "propriedades.txt";

    public void salvar(Propriedade propriedade) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            String linha = propriedade.getClass().getSimpleName() + ";" + propriedade.getTitulo() + ";" + propriedade.getDescricao() + ";" +
                    propriedade.getLocalizacao() + ";" + propriedade.getCapacidade() + ";" + propriedade.getPrecoPorNoite() + ";" +
                    propriedade.getProprietario().getEmail() + ";" + propriedade.estaDisponivel();
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Propriedade> listarTodas() {
        // Implementação omitida aqui por depender de instância de proprietário para atribuir
        return new ArrayList<>();
    }
}