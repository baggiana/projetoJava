import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    private Propriedade propriedade;
    private Cliente cliente;
    private Date checkIn;
    private Date checkOut;
    private double custoTotal;

    public Reserva(Propriedade propriedade, Cliente cliente, Date checkIn, Date checkOut) {
        this.propriedade = propriedade;
        this.cliente = cliente;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.custoTotal = calcularCustoTotal();
        propriedade.setDisponivel(false);
    }

    private double calcularCustoTotal() {
        long diff = checkOut.getTime() - checkIn.getTime();
        int dias = (int) Math.ceil(diff / (1000.0 * 60 * 60 * 24));
        if (dias <= 0) dias = 1;
        return propriedade.calcularPrecoTotal(dias);
    }

    public Propriedade getPropriedade() {
        return propriedade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void imprimirDetalhes() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Confira os detalhes da reserva: ");
        System.out.println("Propriedade: " + propriedade.getTitulo());
        System.out.println("Reservado por: " + cliente.getNome() + ", " + cliente.getEmail());
        System.out.println("Check In: " + sdf.format(checkIn));
        System.out.println("Check Out: " + sdf.format(checkOut));
        System.out.println("Custo Total: R$ " + custoTotal);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Reserva{" +
                "propriedade=" + propriedade.getTitulo() +
                ", cliente=" + cliente.getNome() +
                ", checkIn=" + sdf.format(checkIn) +
                ", checkOut=" + sdf.format(checkOut) +
                ", custoTotal=" + custoTotal +
                '}';
    }
}