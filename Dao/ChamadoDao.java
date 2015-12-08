package persistencia;

import excecao.Excecao;
import java.util.List;
import java.util.ArrayList;
import modelo.Chamado;
import java.io.*;
import java.util.Collections;
import javax.swing.JOptionPane;

public class ChamadoDAO {

    private List<Chamado> lista = new ArrayList<Chamado>();
    public static final String caminho = "chamados.txt";

    public ChamadoDAO() {
        try {
            leArquivo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Arquivo " + caminho + " criado com sucesso.");
        }
    }

    public void adicionar(Chamado chamado) throws Excecao {
        for (Chamado c : lista) {
            if (c.equals(chamado)) {
                throw new Excecao("Chamado Ja Existente");
            }
        }
        lista.add(chamado);
        gravaArquivo(chamado, caminho);
    }

    public void excluir(Chamado chamado) throws Excecao {
        for (Chamado c : lista) {
            if (c.equals(chamado)) {
                lista.remove(chamado);
                atualizaArquivo(lista);
                return;
            }
        }
        throw new Excecao("Chamado Nao Encontrado.");
    }

    public void editar(Chamado chamado) {
        for (Chamado c : lista) {
            if (c.equals(chamado)) {
                lista.remove(c);
                atualizaArquivo(lista);
                lista.add(chamado);
                gravaArquivo(chamado, caminho);
                return;
            }
        }
    }

    public List<Chamado> getLista() {
        Collections.sort(lista);
        return lista;
    }

    public int getProximoId() {
        int id = 0;
        if (lista.isEmpty()) {
            return ++id;
        }

        for (Chamado c : lista) {
            if (id < c.getId()) {
                id = c.getId();
            }
        }

        return ++id;
    }

    public void gravaArquivo(Chamado chamado, String caminho) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file, true);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            saida.println(chamado.getSerialized());
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
                lista.add(Chamado.setSerialized(linha));
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

    public void atualizaArquivo(List<Chamado> lista) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            for (Chamado c : lista) {
                saida.println(c.getSerialized());
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
