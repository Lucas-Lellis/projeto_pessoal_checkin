package projeto.model.entidade;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Voo {

    private String numeroVoo;
    private String companhia;
    private String origem;
    private String destino;
    private LocalTime horario;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm");

    public Voo(String numeroVoo, String companhia, String origem, String destino, LocalTime horario) {
        this.numeroVoo = numeroVoo;
        this.companhia = companhia;
        this.origem = origem;
        this.destino = destino;
        this.horario = horario;
    }

    public String getNumeroVoo() {
        return numeroVoo;
    }

    public void setNumeroVoo(String numeroVoo) {
        this.numeroVoo = numeroVoo;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return String.format("""
                Numero: %s
                Companhia: %s
                Origem: %s
                Destino: %s
                Horario: %s
                """,
                numeroVoo,
                companhia,
                origem,
                destino,
                horario.format(FMT)
        );
    }
}
