package projeto.programa;

import projeto.model.entidade.Bilhete;
import projeto.model.entidade.Checkin;
import projeto.model.entidade.Passageiro;
import projeto.model.entidade.Voo;
import projeto.model.servico.PedidoStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static projeto.model.entidade.ConsoleColor.*;
import static projeto.model.entidade.VooRepository.lerVoos;
import static projeto.model.entidade.VooRepository.voos;

public class Programa {

    static Scanner scan = new Scanner(System.in);
    static List<Bilhete> bilheteEmitidos = new ArrayList<>();

    public static void main(String[] args) {

        int opcao;
        Passageiro passageiro = null;


        System.out.println("=== SISTEMA DE COMPRA DE PASSAGENS ===");
        do {
            System.out.println("""
                    1. Cadastrar Passageiro
                    2. Ver Voos
                    3. Comprar Bilhete
                    4. Check-In
                    5. Sair""");
            System.out.print("Opção: ");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n=== CADASTRO DE PASSAGEIRO ===");
                    passageiro = cadastrarPassageiro();
                }
               case 2 -> {
                   System.out.println("\n=== LISTA DE VOOS ===");
                   lerVoos();
               }
               case 3 -> {
                   System.out.println("\n=== COMPRA DE BILHETE ===");
                   comprarBilhete(passageiro);
               }
               case 4 -> {
                   System.out.println("\n=== VERIFICANDO CHECK-IN ===");
                   verificarCheckin(bilheteEmitidos);
               }
                case 5 -> System.out.println(GREEN + "Finalizando Programa..." + RESET);
                default -> {
                    // Impedir delay do print sob o menu
                    System.out.print("");
                    System.out.println(RED + "Opção Invalida! Tente novamente.\n" + RESET);
                }
            }
        }
        while (opcao != 5);
    }
    public static Passageiro cadastrarPassageiro() {
        try {
            System.out.print("Nome Completo: ");
            // Consumir quebrar de linha
            scan.nextLine();
            String nomePassageiro = scan.nextLine();
            System.out.print("CPF: ");
            String cpfPassageiro = scan.next();
            System.out.print("Data de Nascimento: ");
            LocalDate dataNascimento = LocalDate.parse(scan.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.println(GREEN + "Passageiro Cadastrado com Sucesso!\n" + RESET);
            return new Passageiro(nomePassageiro, cpfPassageiro, dataNascimento);
        }
        catch (DateTimeParseException e) {
            System.out.println(RED + "Data Invalida! Use o formato dd/mm/yyyy.\n" + RESET);
            return null;
        }
        catch (Exception e) {
            System.out.println(RED + "Erro ao Cadastrar Passageiro: \n" + e.getMessage() + RESET);
            return null;
        }
    }
    public static void comprarBilhete(Passageiro passageiro) {
        try {
            if (passageiro == null) {
                System.out.println(RED + "Cadastre um Passageiro Primeiro.\n" + RESET);
                return;
            }

            if (voos.isEmpty()) {
                lerVoos();
            }

            System.out.print("Destino do Voo (RJ, RS, SC, SP): ");
            String destino = scan.next();
            switch (destino.toUpperCase()) {
                case "RJ" -> destino = "Rio de Janeiro";
                case "RS" -> destino = "Rio Grande do Sul";
                case "SC" -> destino = "Santa Catarina";
                case "SP" -> destino = "São Paulo";
                default -> {
                    System.out.println(RED + "Destino Invalido!\n" + RESET);
                    return;
                }
            }

            boolean vooEncontrado = false;
            System.out.println();
            for (Voo voo : voos) {
                if (voo.getDestino().equalsIgnoreCase(destino)) {
                    System.out.println(voo);
                    vooEncontrado = true;
                }
            }

            if (!vooEncontrado) {
                System.out.println(RED + "Voo não Disponivel!\n" + RESET);
                return;
            }

            System.out.print("Escolha o horario (hh:mm): ");
            LocalTime horarioVoo = LocalTime.parse(scan.next(), DateTimeFormatter.ofPattern("HH:mm"));

            Voo vooSelecionado = null;
            for (Voo voo : voos) {
                if (voo.getDestino().equalsIgnoreCase(destino) && voo.getHorario().equals(horarioVoo)) {
                    vooSelecionado = voo;
                    break;
                }
            }

            if (vooSelecionado == null) {
                System.out.println(RED + "Voo com esse Horario não Encontrado.\n" + RESET);
                return;
            }

            // Simulando numero do bilhete e assentos
            int numeroBilhete = (int) (Math.random() * 10000);
            int assento = (int) (Math.random() * 150 + 1);

            Bilhete bilhete = new Bilhete(numeroBilhete, assento, vooSelecionado, passageiro);
            bilheteEmitidos.add(bilhete);
            System.out.println(GREEN + "Bilhete Gerado com Sucesso!\n" + RESET);
            System.out.println(bilhete + "\n");
        }
        catch (DateTimeParseException e) {
            System.out.println(RED + "Formato de Hórario Invalido! Use o formato hh:mm\n." + RESET);
        }
        catch (Exception e) {
            System.out.println(RED + "Erro ao Comprar Bilhete: \n" + e.getMessage() + RESET);
        }
    }
    public static void verificarCheckin(List<Bilhete> bilheteEmitidos) {
        System.out.print("CPF: ");
        String cpf = scan.next();
        Bilhete bilheteSelecionado = null;
        for (Bilhete bilhete : bilheteEmitidos) {
            if (bilhete.getPassageiro().getCpf().equalsIgnoreCase(cpf)) {
                System.out.println(GREEN + "Bilhete Encontrado!\n" + RESET);
                bilheteSelecionado = bilhete;
                System.out.println(bilheteSelecionado);
                break;
            }
        }

        if (bilheteSelecionado == null) {
            System.out.println(RED + "Bilhete não Encontrado para o CPF Informado.\n" + RESET);
            return;
        }

        System.out.print("Horario de Checkin (hh:mm): ");
        LocalTime horarioCheckin = LocalTime.parse(scan.next(), DateTimeFormatter.ofPattern("HH:mm"));

        PedidoStatus pedidoStatus;

        if (horarioCheckin.isBefore(bilheteSelecionado.getVoo().getHorario())) {
            pedidoStatus = PedidoStatus.Aprovado;
            }
        else {
            pedidoStatus = PedidoStatus.Nao_Aprovado;
        }

        Checkin checkin = new Checkin(horarioCheckin, bilheteSelecionado, pedidoStatus);
        System.out.println(GREEN + "Check-in Realizado!\n" + RESET);
        System.out.println(checkin + "\n");
    }
}
