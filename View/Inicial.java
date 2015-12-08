package view;

import excecao.Excecao;
import javax.swing.JOptionPane;
import persistencia.*;

public class Inicial {

    public static void main(String[] args) throws Excecao {

        EmpresaDAO listaEmpresa = new EmpresaDAO();
        ChamadoDAO listaChamado = new ChamadoDAO();
        EquipamentoDAO listaEquipamento = new EquipamentoDAO();
        FuncionarioDAO listaFuncionario = new FuncionarioDAO();
        ServicoDAO listaServico = new ServicoDAO();

        int opc = -1;
        String aux = "";
        do {
            aux = (JOptionPane.showInputDialog(
                    "++++++++++++++++++++\n"
                    + "+ MENU PRINCIPAL\n"
                    + "++++++++++++++++++++\n"
                    + "+ 1 - Chamados\n"
                    + "+ 2 - Funcionarios\n"
                    + "+ 3 - Equipamentos\n"
                    + "+ 4 - Servicos\n"
                    + "+ 5 - Empresas\n"
                    + "++++++++++++++++++++\n"
                    + "+ 0 - Sair\n"
                    + "++++++++++++++++++++"));

            if (aux == null) {
                opc = 0;
                continue;
            }

            try {
                opc = Integer.valueOf(aux);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + "\nValor invalido. Tente Novamente.");
                continue;
            }

            switch (opc) {
                case 1:
                    ViewChamado.menu(listaChamado, listaFuncionario, listaServico, listaEquipamento);
                    break;
                case 2:
                    ViewFuncionario.menu(listaFuncionario);
                    break;
                case 3:
                    ViewEquipamento.menu(listaEquipamento);
                    break;
                case 4:
                    ViewServico.menu(listaServico);
                    break;
                case 5:
                    ViewEmpresa.menu(listaEmpresa);
                    break;
                case 0:
                    int resp = JOptionPane.showConfirmDialog(null, "Realmente deseja sair?");
                    if (resp == 0) {
                        System.exit(0);
                        break;
                    }
                    if (resp == 1) {
                        opc = 1;
                    }
                    if (resp == 2) {
                        opc = 1;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao Invalida");
            }

        } while (opc != 0);
    }
}
