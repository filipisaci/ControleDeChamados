package modelo;

import excecao.Excecao;
import java.text.ParseException;
import java.util.regex.*;

public class Servico implements Comparable<Servico> {

    private Integer id;
    private char status;
    private String nome;

    public Servico() {
    }

    public Servico(Integer id, char status, String nome) {
        this.id = id;
        this.status = status;
        this.nome = nome;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Servico) {
            return this.id.equals(((Servico) obj).getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return "\n" + "ID: " + getId()
                + "\n" + "Status: " + getStatus()
                + "\n" + "Nome: " + getNome();

    }

    public String getSerialized() {
        return getId().toString()
                + ";" + getStatus()
                + ";" + getNome();
    }

    public static Servico setSerialized(String linha) throws Excecao, ParseException {
        String sArr[] = linha.split(";");
        Servico s = new Servico();
        s.setId(Integer.valueOf(sArr[0]));
        s.setStatus(sArr[1].charAt(0));
        s.setNome(sArr[2]);

        return s;
    }

    @Override
    public int compareTo(Servico o) {
        if (this.getId() < o.getId()) {
            return -1;
        }
        if (this.getId() > o.getId()) {
            return 1;
        }
        return 0;
    }
}
