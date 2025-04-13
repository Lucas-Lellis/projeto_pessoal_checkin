package projeto.model.entidade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Passageiro {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MM/yyyy");

    public Passageiro(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return String.format("""
                Nome: %s
                CPF: %s
                Data de Nascimento (mm/yyyy): %s
                """,
                nome,
                cpf,
                dataNascimento.format(FMT)
        );
    }
}
