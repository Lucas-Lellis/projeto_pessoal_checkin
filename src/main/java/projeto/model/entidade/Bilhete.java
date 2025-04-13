package projeto.model.entidade;

public class Bilhete {

    private Integer numeroBilhete;
    private Integer assento;

    private Voo voo;
    private Passageiro passageiro;

    public Bilhete(Integer numeroBilhete, Integer assento, Voo voo, Passageiro passageiro) {
        this.numeroBilhete = numeroBilhete;
        this.assento = assento;
        this.voo = voo;
        this.passageiro = passageiro;
    }

    public Integer getNumeroBilhete() {
        return numeroBilhete;
    }

    public void setNumeroBilhete(Integer numeroBilhete) {
        this.numeroBilhete = numeroBilhete;
    }

    public Integer getAssento() {
        return assento;
    }

    public void setAssento(Integer assento) {
        this.assento = assento;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    @Override
    public String toString() {
        return String.format("""
                Viajante: %s
                CPF: %s
                Bilhete N°: %d
                Assento: %d
                Voo: %s
                """,
                passageiro.getNome(),
                passageiro.getCpf(),
                numeroBilhete,
                assento,
                voo.getNumeroVoo()
        );
    }
}
