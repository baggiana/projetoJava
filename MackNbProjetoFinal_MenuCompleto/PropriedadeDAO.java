import java.util.List;

public interface PropriedadeDAO {
    void salvar(Propriedade propriedade);
    List<Propriedade> listarTodas();
}