package persistencia;

import excecao.Excecao;
import java.util.ArrayList;
import java.util.List;
import modelo.Funcionario;
import java.io.*;
import java.util.Collections;
import javax.swing.JOptionPane;

public class FuncionarioDAO {

    private List<Funcionario> lista = new ArrayList<Funcionario>();
    public static final String caminho = "funcionarios.txt";

    public FuncionarioDAO() {
        try {
            leArquivo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Arquivo " + caminho + " criado com sucesso.");
        }
    }

    public void adicionar(Funcionario funcionario) throws Excecao {
        for (Funcionario f : lista) {
            if (f.equals(funcionario)) {
                throw new Excecao("Funcionario ja Cadastrado.");
            }
        }
        lista.add(funcionario);
        gravaArquivo(funcionario, caminho);
    }

    public void excluir(Funcionario funcionario) throws Excecao {
        for (Funcionario f : lista) {
            if (f.equals(funcionario)) {
                lista.remove(funcionario);
                atualizaArquivo(lista);
                return;
            }
        }
        throw new Excecao("Funcionario Nao Encontrado.");
    }

    public void editar(Funcionario funcionario) throws Excecao {
        for (Funcionario f : lista) {
            if (f.equals(funcionario)) {
                lista.remove(f);
                atualizaArquivo(lista);
                lista.add(f);
                gravaArquivo(f, caminho);
                break;
            }
        }
    }

    public List<Funcionario> getLista() {
        Collections.sort(lista);
        return lista;
    }

    public int getProximoId() {
        int id = 0;
        if (lista.isEmpty()) {
            return ++id;
        }

        for (Funcionario f : lista) {
            if (id < f.getId()) {
                id = f.getId();
            }
        }
        return ++id;
    }

    public Funcionario getFuncionario(Integer id) {
        for (Funcionario s : lista) {
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

    public Funcionario requisitarFuncionario() {
        int opc, numeroFuncionarios = 0;
        boolean flag = false;
        String s = "Selecione o funcionario que solucionou o problema: \n\n";

        for (Funcionario ss : lista) {
            if (ss.getStatus() == 'A') {
                numeroFuncionarios++;
                s = s.concat((ss.getId()) + " - " + (ss.getNome()) + "\n");
            }
        }

        do {
            try {
                opc = Integer.valueOf(JOptionPane.showInputDialog(s));
                if ((opc > 0) && (opc <= numeroFuncionarios)) {
                    flag = true;
                    return getFuncionario(opc);
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionario Invalido");
                }
            } catch (NumberFormatException ex) {
                throw ex;
            }
        } while (!flag);

        return null;
    }

    public void gravaArquivo(Funcionario funcionario, String caminho) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file, true);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            saida.println(funcionario.getSerialized());
            br.flush();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gravar o arquivo: " + caminho);
        } finally {
            try {
                fw.close();
                saida.close();
                br.close();
            } catch (Exception e) {
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
                lista.add(Funcionario.setSerialized(linha));
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

    public void atualizaArquivo(List<Funcionario> lista) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter br = null;
        PrintWriter saida = null;

        try {
            file = new File(caminho);
            fw = new FileWriter(file);
            br = new BufferedWriter(fw);
            saida = new PrintWriter(br);
            for (Funcionario f : lista) {
                saida.println(f.getSerialized());
                br.flush();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o arquivo: " + caminho);
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
