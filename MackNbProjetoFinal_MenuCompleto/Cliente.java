import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Usuario {
    private ArrayList<Reserva> reservasRealizadas = new ArrayList<>();

    public Cliente(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void realizarReserva(Propriedade p, Date checkIn, Date checkOut) {
        if (p.estaDisponivel()) {
            Reserva r = new Reserva(p, this, checkIn, checkOut);
            reservasRealizadas.add(r);
            p.setDisponivel(false);
            System.out.println("Reserva realizada com sucesso!");
        } else {
            System.out.println("Propriedade indisponível para reserva.");
        }
    }

    public void listarReservas() {
        for (Reserva r : reservasRealizadas) {
            System.out.println(r);
        }
    }

    public void imprimirDados() {
        System.out.println("Cliente: " + nome + ", Email: " + email);
    }
}