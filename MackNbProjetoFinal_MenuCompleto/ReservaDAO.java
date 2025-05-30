import java.util.List;

public interface ReservaDAO {
    void salvar(Reserva reserva);
    List<Reserva> listarTodas(List<Propriedade> propriedades, List<Cliente> clientes);
}