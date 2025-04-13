package projeto.model.entidade;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;
import static projeto.model.entidade.ConsoleColor.*;


public class VooRepository {

    public static List<Voo> voos = new ArrayList<>();

    private static final String FILE_PATH = "C:\\Intellij\\Voos.txt";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm");

    public static List<Voo> lerVoos() {


        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha = br.readLine(); //pular cabeçalho
            while ((linha = br.readLine()) != null) {
                String [] campos = linha.split(", ");
                String numeroVoo = campos[0];
                String companhia = campos[1];
                String origem = campos[2];
                String destino = campos[3];
                LocalTime horario = LocalTime.parse(campos[4], FMT);

                voos.add(new Voo(numeroVoo, companhia, origem, destino, horario));
            }
        } catch (IOException e) {
            System.out.println(RED + "Erro ao ler o arquivo de Voos: " + e.getMessage() + RESET);
        }
        for (Voo voo : voos) {
            System.out.println(voo);
        }
        return voos;
    }
}
