package persistencia;

import excecao.Excecao;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipamento;
import java.io.*;
import java.util.Collections;
import javax.swing.JOptionPane;

public class EquipamentoDAO {

    private List<Equipamento> lista = new ArrayList<Equipamento>();
    public static final String caminho = "equipamentos.txt";

    public EquipamentoDAO() {
        try {
            leArquivo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Arquivo " + caminho + " criado com sucesso.");
        }
    }

    public void adicionar(Equipamento equipamento) throws Excecao {
        for (Equipamento e : lista) {
            if (e.equals(equipamento)) {
                throw new Excecao("Equipamento Ja Cadastrado.");
            }
        }
        lista.add(equipamento);
        gravaArquivo(equipamento, caminho);
    }

    public void editar(Equipamento equipamento) {
        for (Equipamento e : lista) {
            if (e.equals(equipamento)) {
                lista.remove(e);
                atualizaArquivo(lista);
                lista.add(equipamento);
                gravaArquivo(equipamento, caminho);
                return;
            }
        }
    }

    public void excluir(Equipamento equipamento) throws Excecao {
        for (Equipamento e : lista) {
            if (e.equals(equipamento)) {
                lista.remove(equipamento);
                atualizaArquivo(lista);
                return;
            }
        }
        throw new Excecao("Equipamento Nao Encontrado.");
    }

    public List<Equipamento> getLista() {
        Collections.sort(lista);
        return lista;
    }

    public int getProximoId() {
        int id = 0;
        if (lista.isEmpty()) {
            return ++id;
        }

        for (Equipamento e : lista) {
            if (id < e.getId()) {
                id = e.getId();
            }
        }
        return ++id;
    }

    public Equipamento getEquipamento(Integer id) {
        for (Equipamento s : lista) {
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

    public Equipamento requisitarEquipamento() {
        int opc, numeroServicos = 0;
        boolean flag = false;
        String s = "Selecione o equipamento que apresenta problemas: \n\n";

        for (Equipamento ss : lista) {
            numeroServicos++;
            s = s.concat((ss.getId()) + " - " + (ss.getNome()) + "\n");
        }

        do {
            try {
                opc = Integer.valueOf(JOptionPane.showInputDialog(s));
                if ((opc > 0) && (opc <= numeroServicos)) {
                    flag = true;
                    return getEquipamento(opc);
                } else {
                    JOptionPane.showMessageDialog(null, "Servico Invalido");
                }
            } catch (NumberFormatException ex) {
                throw ex;
            }
        } while (!flag);

        return null;
    }

    public void gravaArquivo(Equipamento equipamento, String caminho) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file, true);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            saida.println(equipamento.getSerialized());
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
                lista.add(Equipamento.setSerialized(linha));
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

    public void atualizaArquivo(List<Equipamento> lista) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            for (Equipamento e : lista) {
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
