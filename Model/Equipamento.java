package modelo;

import excecao.Excecao;
import java.text.ParseException;
import java.util.regex.*;

public class Equipamento implements Comparable<Equipamento> {

    private Integer id;
    private String nome;
    private String observacao;
    private char status;

    public Equipamento() {
    }

    public Equipamento(Integer id, String nome, String observacao, char status) {
        this.id = id;
        this.status = status;
        this.nome = nome;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) throws Excecao {
        if (validaObservacao(observacao)) {
            this.observacao = observacao;
        } else {
            throw new Excecao("Observacao invalida");
        }
    }

    private boolean validaObservacao(String observacao) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(observacao);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Equipamento) {
            Equipamento e = (Equipamento) obj;
            if (this.getId() == e.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "\n" + "ID: " + getId()
                + "\n" + "Nome: " + getNome()
                + "\n" + "Observacoes: " + getObservacao()
                + "\n" + "Status: " + getStatus();
    }

    public String getSerialized() {
        return getId().toString()
                + ";" + getStatus()
                + ";" + getNome()
                + ";" + getObservacao();
    }

    public static Equipamento setSerialized(String linha) throws Excecao, ParseException {
        String sArr[] = linha.split(";");
        Equipamento e = new Equipamento();
        e.setId(Integer.valueOf(sArr[0]));
        e.setStatus(sArr[1].charAt(0));
        e.setNome(sArr[2]);
        e.setObservacao(sArr[3]);

        return e;
    }

    @Override
    public int compareTo(Equipamento o) {
            if (this.getId() < o.getId()) {
            return -1;
        }
        if (this.getId() > o.getId()) {
            return 1;
        }
        return 0;

    }
    
}
