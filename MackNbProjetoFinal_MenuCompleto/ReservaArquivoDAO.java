import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaArquivoDAO implements ReservaDAO {
    private static final String ARQUIVO = "reservas.txt";

    public void salvar(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String checkInStr = sdf.format(reserva.getCheckIn());
            String checkOutStr = sdf.format(reserva.getCheckOut());
            writer.write(reserva.getPropriedade().getTitulo() + ";" + reserva.getCliente().getEmail() + ";" +
                         checkInStr + ";" + checkOutStr + ";" +
                         reserva.getCustoTotal());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Adicione os parâmetros para as listas de propriedades e clientes já carregadas
    public List<Reserva> listarTodas(List<Propriedade> propriedades, List<Cliente> clientes) {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length < 5) continue;

                String tituloPropriedade = partes[0];
                String emailCliente = partes[1];
                Date checkIn = sdf.parse(partes[2]);
                Date checkOut = sdf.parse(partes[3]);
                // double custoTotal = Double.parseDouble(partes[4]); // Não precisa, será recalculado

                Propriedade propriedade = propriedades.stream()
                        .filter(p -> p.getTitulo().equals(tituloPropriedade))
                        .findFirst().orElse(null);

                Cliente cliente = clientes.stream()
                        .filter(c -> c.getEmail().equals(emailCliente))
                        .findFirst().orElse(null);

                if (propriedade != null && cliente != null) {
                    Reserva reserva = new Reserva(propriedade, cliente, checkIn, checkOut);
                    reservas.add(reserva);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservas;
    }
}