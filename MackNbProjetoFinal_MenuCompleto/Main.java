/* Ana Luisa Licurci Baggio - 10297891
 * Ana Clara Maciel - 10434611
 */

import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Propriedade> propriedades = new ArrayList<>();
    private static UsuarioDAO usuarioDAO = new UsuarioArquivoDAO();
    private static PropriedadeDAO propriedadeDAO = new PropriedadeArquivoDAO();
    private static ReservaDAO reservaDAO = new ReservaArquivoDAO();

    public static void main(String[] args) {
        carregarUsuarios();
        carregarPropriedades();

        while (true) {
            System.out.println("\n-*-*-*-* MackNb Plataforma de Locação -*-*-*-*");
            System.out.println("1. Vamos se cadastrar cliente");
            System.out.println("2. Vamos se cadastrar proprietário");
            System.out.println("3. Entrar como Proprietário");
            System.out.println("4. Entrar como Cliente");
            System.out.println("0. Desejo encerar!");
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarProprietario();
                    break;
                case 3:
                    proprietarioMenu();
                    break;
                case 4:
                    clienteMenu();
                    break;
                case 0:
                    System.out.println("Até breve user...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void clienteMenu() {
        Cliente cliente = autenticarCliente();
        if (cliente == null) {
            System.out.println("Autenticação falhou.");
            return;
        }

        while (true) {
            System.out.println("\n-*-*-*-* Menu do Cliente -*-*-*-*");
            System.out.println("1. Listar propriedades disponíveis");
            System.out.println("2. Fazer reserva");
            System.out.println("3. Listar minhas reservas");
            System.out.println("0. Voltar");
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    listarPropriedadesDisponiveis();
                    break;
                case 2:
                    fazerReserva(cliente);
                    break;
                case 3:
                    cliente.listarReservas();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void proprietarioMenu() {
        Proprietario prop = autenticarProprietario();
        if (prop == null) {
            System.out.println("Autenticação falhou.");
            return;
        }

        while (true) {
            System.out.println("\n-*-*-*-* Menu do Proprietário -*-*-*-*");
            System.out.println("1. Cadastrar nova propriedade");
            System.out.println("2. Listar minhas propriedades");
            System.out.println("3. Listar propriedades alugadas");
            System.out.println("0. Voltar");
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarPropriedade(prop);
                    break;
                case 2:
                    prop.listarPropriedades();
                    break;
                case 3:
                    prop.listarPropriedadesAlugadas();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Cliente cliente = new Cliente(nome, email, senha);
        usuarios.add(cliente);
        usuarioDAO.salvar(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarProprietario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Proprietario prop = new Proprietario(nome, email, senha);
        usuarios.add(prop);
        usuarioDAO.salvar(prop);
        System.out.println("Proprietário cadastrado com sucesso!");
    }

    private static void cadastrarPropriedade(Proprietario prop) {
        System.out.println("Tipo (1=Casa, 2=Apto, 3=Sitio): ");
        int tipo = lerOpcao();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Localização: ");
        String localizacao = scanner.nextLine();
        System.out.print("Capacidade: ");
        int capacidade = lerOpcao();
        System.out.print("Preço por noite: ");
        double preco = Double.parseDouble(scanner.nextLine());

        Propriedade p = null;
        switch (tipo) {
            case 1:
                System.out.print("Possui piscina (true/false): ");
                boolean piscina = Boolean.parseBoolean(scanner.nextLine());
                System.out.print("Preço por pessoa: ");
                double precoPorPessoa = Double.parseDouble(scanner.nextLine());
                p = new Casa(titulo, descricao, localizacao, capacidade, preco, prop, piscina, precoPorPessoa);
                break;
            case 2:
                System.out.print("Andar: ");
                int andar = lerOpcao();
                System.out.print("Taxa extra: ");
                double taxa = Double.parseDouble(scanner.nextLine());
                p = new Apartamento(titulo, descricao, localizacao, capacidade, preco, prop, andar, taxa);
                break;
            case 3:
                System.out.print("Área total (m²): ");
                double area = Double.parseDouble(scanner.nextLine());
                p = new Sitio(titulo, descricao, localizacao, capacidade, preco, prop, area);
                break;
            default:
                System.out.println("Tipo inválido.");
        }
        if (p != null) {
            prop.cadastrarPropriedade(p);
            propriedades.add(p);
            propriedadeDAO.salvar(p);
            System.out.println("Propriedade cadastrada com sucesso!");
        }
    }

    private static void listarPropriedadesDisponiveis() {
        for (Propriedade p : propriedades) {
            if (p.estaDisponivel()) p.imprimirDados();
        }
    }

    private static void fazerReserva(Cliente cliente) {
        listarPropriedadesDisponiveis();
        System.out.print("Digite o título da propriedade: ");
        String titulo = scanner.nextLine();
        Propriedade p = propriedades.stream()
                .filter(prop -> prop.getTitulo().equalsIgnoreCase(titulo) && prop.estaDisponivel())
                .findFirst().orElse(null);
        if (p == null) {
            System.out.println("Propriedade não encontrada ou indisponível.");
            return;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.print("Data check-in (dd/MM/yyyy): ");
            String inStr = scanner.nextLine();
            System.out.print("Data check-out (dd/MM/yyyy): ");
            String outStr = scanner.nextLine();
            Date checkIn = sdf.parse(inStr);
            Date checkOut = sdf.parse(outStr);
            Reserva r = new Reserva(p, cliente, checkIn, checkOut);
            cliente.realizarReserva(p, checkIn, checkOut);
            reservaDAO.salvar(r);
            System.out.println("Reserva realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro na reserva: " + e.getMessage());
        }
    }

    private static Cliente autenticarCliente() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return usuarios.stream()
                .filter(u -> u instanceof Cliente && u.getEmail().equals(email) && u.getSenha().equals(senha))
                .map(u -> (Cliente) u).findFirst().orElse(null);
    }

    private static Proprietario autenticarProprietario() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return usuarios.stream()
                .filter(u -> u instanceof Proprietario && u.getEmail().equals(email) && u.getSenha().equals(senha))
                .map(u -> (Proprietario) u).findFirst().orElse(null);
    }

    private static void carregarUsuarios() {
        usuarios = usuarioDAO.listarTodos();
        if (usuarios == null) usuarios = new ArrayList<>();
    }

    private static void carregarPropriedades() {
        propriedades = propriedadeDAO.listarTodas();
        if (propriedades == null) propriedades = new ArrayList<>();
    }
}