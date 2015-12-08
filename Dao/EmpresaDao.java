package persistencia;

import excecao.Excecao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Empresa;

public class EmpresaDAO {

    private List<Empresa> lista = new ArrayList<Empresa>();
    public static final String caminho = "empresas.txt";

    public EmpresaDAO() {
        try {
            leArquivo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Arquivo " + caminho + " criado com sucesso.");
        }
    }

    public void adicionar(Empresa empresa) throws Excecao {
        for (Empresa e : lista) {
            if (e.equals(empresa)) {
                throw new Excecao("Empresa Ja Cadastrada.");
            }
        }
        lista.add(empresa);
        gravaArquivo(empresa, caminho);
    }

    public void excluir(Empresa empresa) throws Excecao {
        for (Empresa e : lista) {
            if (e.equals(empresa)) {
                lista.remove(empresa);
                atualizaArquivo(lista);
                return; 
            }
        }
        throw new Excecao("Empresa Nao Encontrada.");
    }

    public void editar(Empresa empresa) {
        for (Empresa e : lista) {
            if (e.equals(empresa)) {
                lista.remove(e);
                atualizaArquivo(lista);
                lista.add(empresa);
                gravaArquivo(empresa, caminho);
                return;
            }
        }
    }

    public List<Empresa> getLista() {
        Collections.sort(lista);
        return lista;
    }

    /*
     //Filtro Nome
     public List<Empresa> getLista(String nome) {
     List<Empresa> aux = new ArrayList<Empresa>();
     for (Empresa e : lista) {
     if (e.getNome().toLowerCase().startsWith(nome.toLowerCase())) {
     aux.add(e);
     }
     }
     return aux;
     }
     */
    public int getProximoId() {
        int id = 0;
        if (lista.isEmpty()) {
            return ++id;
        }

        for (Empresa e : lista) {
            if (id < e.getId()) {
                id = e.getId();
            }
        }
        return ++id;
    }

    public void gravaArquivo(Empresa empresa, String caminho) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file, true);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            saida.println(empresa.getSerialized());
            br.flush();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gravar o arquivo: " + caminho);
        } finally {
            try {
                fw.close();
                saida.close();
                br.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao fechar o arquivo: " + caminho);
            }
        }
    }

    public void leArquivo() {
        lista.clear();
        FileReader file = null;
        BufferedReader br = null;

        try {
            file = new FileReader(caminho);
            br = new BufferedReader(file);
            String linha = null;
            while ((linha = br.readLine()) != null) {
                lista.add(Empresa.setSerialized(linha));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao ler o arquivo: " + caminho);
        } finally {
            try {
                br.close();
                file.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao fechar o arquivo: " + caminho);
            }
        }
    }

    public void atualizaArquivo(List<Empresa> lista) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            for (Empresa e : lista) {
                saida.println(e.getSerialized());
                br.flush();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o arquivo: " + caminho);;
        } finally {
            try {
                fw.close();
                saida.close();
                br.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao fechar o arquivo: " + caminho);
            }
        }
    }
}
