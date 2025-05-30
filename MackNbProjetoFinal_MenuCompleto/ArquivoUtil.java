import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {
    public static void escrever(String caminho, String conteudo, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, append))) {
            writer.write(conteudo);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> ler(String caminho) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }
}