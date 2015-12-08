package modelo;

import excecao.*;
import java.util.regex.*;

public class Endereco {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco() {
    }

    public Endereco(String rua, String numero, String bairro, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) throws Excecao {
        if (validaRua(rua)) {
            this.rua = rua;
        } else {
            throw new Excecao("Rua Invalida");
        }
    }

    private boolean validaRua(String rua) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(rua);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) throws Excecao {
        if (validaNumero(numero)) {
            this.numero = numero;
        } else {
            throw new Excecao("Numero Invalido");
        }
    }

    private boolean validaNumero(String numero) {
        Pattern padrao = Pattern.compile("\\d{1,4}");
        Matcher comparador = padrao.matcher(numero);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) throws Excecao {
        if (validaBairro(bairro)) {
            this.bairro = bairro;
        } else {
            throw new Excecao("Bairro Invalido");
        }
    }

    private boolean validaBairro(String bairro) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(bairro);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) throws Excecao {
        if (validaCidade(cidade)) {
            this.cidade = cidade;
        } else {
            throw new Excecao("Cidade Invalida");
        }
    }

    private boolean validaCidade(String cidade) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(cidade);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) throws Excecao {
        if (validaEstado(estado)) {
            this.estado = estado;
        } else {
            throw new Excecao("Estado Invalido");
        }
    }

    private boolean validaEstado(String estado) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(estado);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Endereco) {
            return this.rua.equals(((Endereco) obj).getRua())
                    && this.numero.equals(((Endereco) obj).getNumero())
                    && this.bairro.equals(((Endereco) obj).getBairro())
                    && this.cidade.equals(((Endereco) obj).getCidade())
                    && this.estado.equals(((Endereco) obj).getEstado());
        }
        return false;
    }

    @Override
    public String toString() {

        return "\n Rua: " + getRua()
                + "\n Numero: " + getNumero()
                + "\n Bairro: " + getBairro()
                + "\n Cidade: " + getCidade()
                + "\n Estado: " + getEstado();
    }
}
