package modelo;

import excecao.Excecao;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chamado implements Comparable<Chamado> {

    private Integer id;
    private Date dataAbertura;
    private Date ExecucaoInicial;
    private String solicitante;
    private String departamento;
    private Servico tipoServico;
    private Equipamento equipamento;
    private String problema;
    private String observacao;
    private Date ExecucaoFinal;
    private Funcionario tecnicoResponsavel;
    private String solucao;
    private char status;

    public Chamado() {
    }

    public Chamado(Integer id, char status, Date dataAbertura, Date ExecucaoInicial, String solicitante, String departamento, Servico tipoServico, Equipamento equipamento, String problema, String observacao, Date ExecucaoFinal, Funcionario tecnicoResponsavel, String solucao) {
        this.id = id;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.ExecucaoInicial = ExecucaoInicial;
        this.solicitante = solicitante;
        this.departamento = departamento;
        this.tipoServico = tipoServico;
        this.equipamento = equipamento;
        this.problema = problema;
        this.observacao = observacao;
        this.ExecucaoFinal = ExecucaoFinal;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.solucao = solucao;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getExecucaoInicial() {
        return ExecucaoInicial;
    }

    public void setExecucaoInicial(Date ExecucaoInicial) {
        this.ExecucaoInicial = ExecucaoInicial;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) throws Excecao {
        if (validaSolicitante(solicitante)) {
            this.solicitante = solicitante;
        } else {
            throw new Excecao("Solicitante invalido");
        }
    }

    private boolean validaSolicitante(String solicitante) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(solicitante);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) throws Excecao {
        if (validaDepartamento(departamento)) {
            this.departamento = departamento;
        } else {
            throw new Excecao("Departamento invalido");
        }
    }

    private boolean validaDepartamento(String departamento) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s]+\\d*");
        Matcher comparador = padrao.matcher(departamento);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public Servico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Servico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) throws Excecao {
        if (validaProblema(problema)) {
            this.problema = problema;
        } else {
            throw new Excecao("Problema invalido");
        }
    }

    private boolean validaProblema(String problema) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(problema);

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
            throw new Excecao("Observacao invalido");
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

    public Date getExecucaoFinal() {
        return ExecucaoFinal;
    }

    public void setExecucaoFinal(Date ExecucaoFinal) {
        this.ExecucaoFinal = ExecucaoFinal;
    }

    public Funcionario getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(Funcionario tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) throws Excecao {
        if (validaSolucao(solucao)) {
            this.solucao = solucao;
        } else {
            throw new Excecao("Solucao invalido");
        }
    }

    private boolean validaSolucao(String solucao) {
        Pattern padrao = Pattern.compile("\\S[a-zA-Z\\s\\D]+");
        Matcher comparador = padrao.matcher(solucao);

        if (comparador.matches()) {
            return true;
        }

        return false;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Chamado) {
            return this.getId().equals(((Chamado) obj).getId());
        }
        return false;
    }

    @Override
    public String toString() {
        DateFormat d = DateFormat.getDateInstance();

        if (getStatus() == 'F') {
            return "\n ID: " + getId()
                    + "\n Data Abertura do Chamado: " + d.format(getDataAbertura())
                    + "\n Solicitante: " + getSolicitante()
                    + "\n Departamento: " + getDepartamento()
                    + "\n Equipamento: " + getEquipamento().getNome()
                    + "\n Tipo do Servico: " + getTipoServico().getNome()
                    + "\n Problema Relatado: " + getProblema()
                    + "\n Observacao: " + getObservacao()
                    + "\n========================"
                    + "\n Inicio Execucao: " + d.format(getExecucaoInicial())
                    + "\n Final Execucao: " + d.format(getExecucaoFinal())
                    + "\n Tecnico Responsavel: " + getTecnicoResponsavel().getNome()
                    + "\n Solucao Executada: " + getSolucao()
                    + "\n Status: " + getStatus();
        }
        return "\n ID: " + getId()
                + "\n Data Abertura do Chamado: " + d.format(getDataAbertura())
                + "\n Solicitante: " + getSolicitante()
                + "\n Departamento: " + getDepartamento()
                + "\n Equipamento: " + getEquipamento().getNome()
                + "\n Tipo do Servico: " + getTipoServico().getNome()
                + "\n Problema Relatado: " + getProblema()
                + "\n Observacao: " + getObservacao()
                + "\n Inicio Execucao: " + d.format(getExecucaoInicial())
                + "\n Status: " + getStatus();
    }

    public String getSerialized() {
        DateFormat d = DateFormat.getDateInstance();

        if (getStatus() == 'F') {
            return getId().toString()
                    + ";" + getStatus()
                    + ";" + d.format(dataAbertura)
                    + ";" + d.format(ExecucaoInicial)
                    + ";" + getSolicitante()
                    + ";" + getDepartamento()
                    + ";" + getTipoServico().getSerialized()
                    + ";" + getEquipamento().getSerialized()
                    + ";" + getProblema()
                    + ";" + getObservacao()
                    + ";" + d.format(ExecucaoFinal)
                    + ";" + getTecnicoResponsavel().getSerialized()
                    + ";" + getSolucao();
        }

        return getId().toString()
                + ";" + getStatus()
                + ";" + d.format(dataAbertura)
                + ";" + d.format(ExecucaoInicial)
                + ";" + getSolicitante()
                + ";" + getDepartamento()
                + ";" + getTipoServico().getSerialized()
                + ";" + getEquipamento().getSerialized()
                + ";" + getProblema()
                + ";" + getObservacao()
                + ";" + null
                + ";" + null
                + ";" + "Ainda nao solucionado.";
    }

    public static Chamado setSerialized(String linha) throws Excecao, ParseException {
        String sArr[] = linha.split(";");
        char statusAux = sArr[1].charAt(0);
        DateFormat d = DateFormat.getDateInstance();

        Chamado c = new Chamado();
        Servico s = new Servico();
        Funcionario f = new Funcionario();
        Equipamento e = new Equipamento();
        Endereco ee = new Endereco();

        if (statusAux == 'F') {
            c.setId(Integer.valueOf(sArr[0]));
            c.setStatus(sArr[1].charAt(0));
            c.setDataAbertura(d.parse(sArr[2]));
            c.setExecucaoInicial(d.parse(sArr[3]));
            c.setSolicitante(sArr[4]);
            c.setDepartamento(sArr[5]);

            s.setId(Integer.valueOf(sArr[6]));
            s.setStatus(sArr[7].charAt(0));
            s.setNome(sArr[8]);
            c.setTipoServico(s);

            e.setId(Integer.valueOf(sArr[9]));
            e.setStatus(sArr[10].charAt(0));
            e.setNome(sArr[11]);
            e.setObservacao(sArr[12]);
            c.setEquipamento(e);

            c.setProblema(sArr[13]);
            c.setObservacao(sArr[14]);
            c.setExecucaoFinal(d.parse(sArr[15]));

            f.setId(Integer.valueOf(sArr[16]));
            f.setStatus(sArr[17].charAt(0));
            f.setNome(sArr[18]);
            ee.setRua(sArr[19]);
            ee.setNumero(sArr[20]);
            ee.setBairro(sArr[21]);
            ee.setCidade(sArr[22]);
            ee.setEstado(sArr[23]);
            f.setEndereco(ee);
            f.setTelefone(sArr[24]);
            f.setEmail(sArr[25]);
            f.setCpf(sArr[26]);
            f.setDataNascimento(sArr[27]);
            f.setCargo(sArr[28]);
            f.setSalario(Float.valueOf(sArr[29]));
            c.setTecnicoResponsavel(f);

            c.setSolucao(sArr[30]);
        } else {
            if (statusAux == 'A') {
                c.setId(Integer.valueOf(sArr[0]));
                c.setStatus(sArr[1].charAt(0));
                c.setDataAbertura(d.parse(sArr[2]));
                c.setExecucaoInicial(d.parse(sArr[3]));
                c.setSolicitante(sArr[4]);
                c.setDepartamento(sArr[5]);

                s.setId(Integer.valueOf(sArr[6]));
                s.setStatus(sArr[7].charAt(0));
                s.setNome(sArr[8]);
                c.setTipoServico(s);

                e.setId(Integer.valueOf(sArr[9]));
                e.setStatus(sArr[10].charAt(0));
                e.setNome(sArr[11]);
                e.setObservacao(sArr[12]);
                c.setEquipamento(e);

                c.setProblema(sArr[13]);
                c.setObservacao(sArr[14]);
                c.setExecucaoFinal(null);
                //Paramos aqui
                c.setTecnicoResponsavel(null);
                c.setSolucao("Ainda nao resolvido");
            }
        }
        return c;
    }

    @Override
    public int compareTo(Chamado o) {
        if (this.getId() < o.getId()) {
            return -1;
        }
        if (this.getId() > o.getId()) {
            return 1;
        }
        return 0;

    }

}
