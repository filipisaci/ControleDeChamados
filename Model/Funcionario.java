package modelo;

import excecao.Excecao;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.*;

public class Funcionario extends Pessoa {

    private String cargo;
    private Float salario;

    public Funcionario() {
    }

    public Funcionario(Integer id, String nome, Endereco endereco, char status, String telefone, String email, String cpf, Date dataNascimento, String cargo, Float salario) {
        super(id, nome, endereco, status, telefone, email, cpf, dataNascimento);
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) throws Excecao {
        if (validaCargo(cargo)) {
            this.cargo = cargo;
        } else {
            throw new Excecao("Cargo invalido");
        }
    }

    private boolean validaCargo(String cargo) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(cargo);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) throws Excecao {
        if (validaSalario(salario)) {
            this.salario = salario;
        } else {
            throw new Excecao("Salario deve ser igual ou superior a um salario minimo (R$ 678,00).");
        }
    }

    private boolean validaSalario(Float salario) {
        if (salario >= 678.0f) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n CPF: " + getCpf()
                + "\n Cargo: " + getCargo()
                + "\n Data Nascimento: " + getDataNascimento()
                + "\n Salario: " + getSalario();
    }

    public String getSerialized() {
        return getId().toString()
                + ";" + getStatus()
                + ";" + getNome()
                + ";" + getEndereco().getRua()
                + ";" + getEndereco().getNumero().toString()
                + ";" + getEndereco().getBairro()
                + ";" + getEndereco().getCidade()
                + ";" + getEndereco().getEstado()
                + ";" + getTelefone()
                + ";" + getEmail()
                + ";" + getCpf()
                + ";" + getDataNascimento()
                + ";" + getCargo()
                + ";" + getSalario();
    }

    public static Funcionario setSerialized(String linha) throws Excecao, ParseException {
        String sArr[] = linha.split(";");
        Funcionario f = new Funcionario();
        Endereco e = new Endereco();
        f.setId(Integer.valueOf(sArr[0]));
        f.setStatus(sArr[1].charAt(0));
        f.setNome(sArr[2]);
        e.setRua(sArr[3]);
        e.setNumero(sArr[4]);
        e.setBairro(sArr[5]);
        e.setCidade(sArr[6]);
        e.setEstado(sArr[7]);
        f.setTelefone(sArr[8]);
        f.setEmail(sArr[9]);
        f.setCpf(sArr[10]);
        f.setDataNascimento(sArr[11]);
        f.setCargo(sArr[12]);
        f.setSalario(Float.valueOf(sArr[13]));

        f.setEndereco(e);

        return f;
    }
}
