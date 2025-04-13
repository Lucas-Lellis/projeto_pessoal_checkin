package projeto.model.entidade;

import projeto.model.servico.PedidoStatus;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Checkin {

    private LocalTime horario;

    private Bilhete bilhete;
    private PedidoStatus status;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm");

    public Checkin(LocalTime horario, Bilhete bilhete, PedidoStatus status) {
        this.horario = horario;
        this.bilhete = bilhete;
        this.status = status;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Bilhete getBilhete() {
        return bilhete;
    }

    public void setBilhete(Bilhete bilhete) {
        this.bilhete = bilhete;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("""
                Horario do Check-In: %s
                Voo: %s
                Status: %s
                """,
                horario.format(FMT),
                bilhete.getVoo().getNumeroVoo(),
                status
        );
    }
}
