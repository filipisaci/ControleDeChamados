package modelo;

import excecao.Excecao;
import java.text.ParseException;
import java.util.regex.*;

public class Empresa implements Comparable<Empresa>{

    private Integer id;
    private char status;
    private String cnpj;
    private String razaoSocial;
    private String nome;
    private Endereco endereco;
    private String contato;
    private String telefone;

    public Empresa() {
    }

    public Empresa(Integer id, char status, String cnpj, String razaoSocial, String nome, Endereco endereco, String contato, String telefone) {

        this.id = id;
        this.status = status;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.telefone = telefone;

    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) throws Excecao {
        if (validaCnpj(cnpj)) {
            this.cnpj = cnpj;
        } else {
            throw new Excecao("CNPJ invalido");
        }

    }

    private boolean validaCnpj(String cnpj) {
        Pattern padrao = Pattern.compile("\\d{14}");
        Matcher comparador = padrao.matcher(cnpj);

        if (comparador.matches()) {
            return true;
        }
        return false;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) throws Excecao {
        if (validaContato(contato)) {
            this.contato = contato;
        } else {
            throw new Excecao("Contato invalido");
        }
    }

    private boolean validaContato(String contato) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(contato);

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
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
            throw new Excecao("Telefone Invalido.");
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Empresa) {
            return this.cnpj.equals(((Empresa) obj).getCnpj());
        }
        return false;
    }

    @Override
    public String toString() {
        return "\n ID: " + getId()
                + "\n CNPJ: " + getCnpj()
                + "\n Razao Social: " + getRazaoSocial()
                + getEndereco()
                + "\n Telefone: " + getTelefone();
    }

    public String getSerialized() {
        return getId().toString()
                + ";" + getStatus()
                + ";" + getCnpj()
                + ";" + getRazaoSocial()
                + ";" + getNome()
                + ";" + getEndereco().getRua()
                + ";" + getEndereco().getNumero()
                + ";" + getEndereco().getBairro()
                + ";" + getEndereco().getCidade()
                + ";" + getEndereco().getEstado()
                + ";" + getContato()
                + ";" + getTelefone();
    }

    public static Empresa setSerialized(String linha) throws Excecao, ParseException {
        String sArr[] = linha.split(";");
        Empresa e = new Empresa();
        Endereco ee = new Endereco();

        e.setId(Integer.valueOf(sArr[0]));
        e.setStatus(sArr[1].charAt(0));
        e.setCnpj(sArr[2]);
        e.setRazaoSocial(sArr[3]);
        e.setNome(sArr[4]);
        ee.setRua(sArr[5]);
        ee.setNumero(sArr[6]);
        ee.setBairro(sArr[7]);
        ee.setCidade(sArr[8]);
        ee.setEstado(sArr[9]);
        e.setContato(sArr[10]);
        e.setTelefone(sArr[11]);

        e.setEndereco(ee);

        return e;
    }
    @Override
    public int compareTo(Empresa o) {
        if (this.getId() < o.getId()) {
            return -1;
        }
        if (this.getId() > o.getId()) {
            return 1;
        }
        return 0;
    }
}
