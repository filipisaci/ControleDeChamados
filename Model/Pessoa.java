package modelo;

import excecao.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.regex.*;

public abstract class Pessoa implements Comparable<Pessoa> {

    private Integer id;
    private String nome;
    private Endereco endereco;
    private char status;
    private String telefone;
    private String email;
    private String cpf;
    private Date dataNascimento;

    public Pessoa() {
    }

    public Pessoa(Integer id, String nome, Endereco endereco, char status, String telefone, String email, String cpf, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.status = status;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Excecao {
        if (validaNome(nome)) {
            this.nome = nome;
        } else {
            throw new Excecao("Nome invalido");
        }
    }

    private boolean validaNome(String nome) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(nome);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws Excecao {
        if (validaTelefone(telefone)) {
            this.telefone = telefone;
        } else {
            throw new Excecao("Telefone invalido");
        }
    }

    private boolean validaTelefone(String telefone) {
        Pattern padrao = Pattern.compile("\\d{10,11}");
        Matcher comparador = padrao.matcher(telefone);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Excecao {
        if (validaEmail(email)) {
            this.email = email;
        } else {
            throw new Excecao("Email invalido");
        }
    }

    private boolean validaEmail(String email) {
        Pattern padrao = Pattern.compile("\\S.+@\\S.+\\.[a-z]{2,3}(\\.[a-z]{0,2})*");
        Matcher comparador = padrao.matcher(email);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws Excecao {
        if (validaCpf(cpf)) {
            this.cpf = cpf;
        } else {
            throw new Excecao("CPF Invalido");
        }
    }

    private boolean validaCpf(String cpf) {
        Pattern padrao = Pattern.compile("\\d{11}");
        Matcher comparador = padrao.matcher(cpf);

        if (comparador.matches()) {
            return true;
        }
        return false;
    }

    public String getDataNascimento() {
        DateFormat d = DateFormat.getDateInstance();
        return d.format(dataNascimento);
    }

    public void setDataNascimento(String dataNascimento) throws Excecao, ParseException {
        if (validaDataNascimento(dataNascimento)) {
            DateFormat d = DateFormat.getDateInstance();
            this.dataNascimento = d.parse(dataNascimento);
        } else {
            throw new Excecao("Data invalida");
        }
    }

    private boolean validaDataNascimento(String dataNascimento) {
        DateFormat d = DateFormat.getDateInstance();

        try {
            Date data = d.parse(dataNascimento);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pessoa) {
            return this.cpf.equals(((Pessoa) obj).getCpf());
        }
        return false;
    }

    @Override
    public String toString() {
        return "\n Id: " + getId()
                + "\n Status: " + getStatus()
                + "\n Nome: " + getNome()
                + getEndereco()
                + "\n Telefone: " + getTelefone()
                + "\n E-mail: " + getEmail();
    }

    @Override
    public int compareTo(Pessoa o) {
            if (this.getId() < o.getId()) {
            return -1;
        }
        if (this.getId() > o.getId()) {
            return 1;
        }
        return 0;

    }
    
    
}
