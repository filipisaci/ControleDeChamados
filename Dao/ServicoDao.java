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
import modelo.Servico;

public class ServicoDAO {

    private List<Servico> lista = new ArrayList<Servico>();
    public static final String caminho = "servicos.txt";

    public ServicoDAO() {
        try {
            leArquivo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Arquivo " + caminho + " criado com sucesso.");
        }
    }

    public void adicionar(Servico servico) throws Excecao {
        for (Servico s : lista) {
            if (s.equals(servico)) {
                throw new Excecao("Servico Ja Cadastrado.");
            }
        }
        lista.add(servico);
        gravaArquivo(servico, caminho);
    }

    public void editar(Servico servico) {
        for (Servico s : lista) {
            if (s.equals(servico)) {
                lista.remove(s);
                atualizaArquivo(lista);
                lista.add(servico);
                gravaArquivo(servico, caminho);
                return;
            }
        }
    }

    public void excluir(Servico servico) throws Excecao {
        for (Servico s : lista) {
            if (s.equals(servico)) {
                lista.remove(servico);
                atualizaArquivo(lista);
                return;
            }
        }
        throw new Excecao("Servico Nao Encontrado.");
    }

    public List<Servico> getLista() {
        Collections.sort(lista);
        return lista;
    }

    public int getProximoId() {
        int id = 0;
        if (lista.isEmpty()) {
            return ++id;
        }

        for (Servico s : lista) {
            if (id < s.getId()) {
                id = s.getId();
            }
        }
        return ++id;
    }

    public Servico getServico(Integer id) {
        for (Servico s : lista) {
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

    public Servico requisitarServico() {
        int opc, numeroServicos = 0;
        boolean flag = false;
        String s = "Servicos: \n\n";

        for (Servico ss : lista) {
            numeroServicos++;
            s = s.concat((ss.getId()) + " - " + (ss.getNome()) + "\n");
        }

        do {
            try {
                opc = Integer.valueOf(JOptionPane.showInputDialog(s));
                if ((opc > 0) && (opc <= numeroServicos)) {
                    flag = true;
                    return getServico(opc);
                } else {
                    JOptionPane.showMessageDialog(null, "Servico Invalido");
                }
            } catch (NumberFormatException ex) {
                throw ex;
            }
        } while (!flag);

        return null;
    }

    public void gravaArquivo(Servico servico, String caminho) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file, true);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            saida.println(servico.getSerialized());
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
                lista.add(Servico.setSerialized(linha));
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

    public void atualizaArquivo(List<Servico> lista) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            for (Servico s : lista) {
                saida.println(s.getSerialized());
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
