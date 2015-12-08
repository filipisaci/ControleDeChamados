package view;

import excecao.Excecao;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Chamado;
import persistencia.*;
import modelo.*;

public class ViewChamado {

    public static void menu(ChamadoDAO listaChamado, FuncionarioDAO listaFuncionario, ServicoDAO listaServico, EquipamentoDAO listaEquipamento) {
        int opc = -1;
        String aux = "";
        boolean flagFuncionario = false, flagServico = false, flagEquipamento = false;

        do {
            aux = JOptionPane.showInputDialog(
                    "++++++++++++++++++++\n"
                    + "+ MENU CHAMADO\n"
                    + "++++++++++++++++++++\n"
                    + "+ 1 - Cadastrar\n"
                    + "+ 2 - Editar/Baixar\n"
                    + "+ 3 - Remover\n"
                    + "+ 4 - Listar\n"
                    + "++++++++++++++++++++\n"
                    + "+ 0 - Sair\n"
                    + "++++++++++++++++++++");

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
                    for (Funcionario f : listaFuncionario.getLista()) {
                        if (f.getStatus() == 'A') {
                            flagFuncionario = true;
                        }
                    }

                    for (Servico s : listaServico.getLista()) {
                        if (s.getStatus() == 'A') {
                            flagServico = true;
                        }
                    }

                    for (Equipamento e : listaEquipamento.getLista()) {
                        if (e.getStatus() == 'A') {
                            flagEquipamento = true;
                        }
                    }

                    if (!listaFuncionario.getLista().isEmpty() && flagFuncionario) {
                        if (!listaServico.getLista().isEmpty() && flagServico) {
                            if (!listaEquipamento.getLista().isEmpty() && flagEquipamento) {
                                cadastrar(listaChamado, listaServico, listaEquipamento);
                            } else {
                                JOptionPane.showMessageDialog(null, "Impossivel registrar chamado. Nao existem equipamentos ativos cadastrados.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Impossivel registrar chamado. Nao existem servicos ativos cadastrados.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Impossivel registrar chamado. Nao existem funcionarios ativos cadastrados.");
                    }
                    break;
                case 2:
                    editar(listaChamado, listaServico, listaEquipamento, listaFuncionario);
                    break;
                case 3:
                    remover(listaChamado);
                    break;
                case 4:
                    listar(listaChamado);
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao Invalida!");
            }
        } while (opc != 0);
    }

    private static void cadastrar(ChamadoDAO listaChamado, ServicoDAO listaServico, EquipamentoDAO listaEquipamento) {
        Chamado c = new Chamado();
        String s;
        boolean flag;

        c.setId(listaChamado.getProximoId());
        c.setStatus('A');
        c.setDataAbertura(new Date());
        c.setExecucaoInicial(new Date());

        do {
            flag = false;
            try {
                s = JOptionPane.showInputDialog("Nome do solicitante:");
                c.setSolicitante(s);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro Cancelado");
                return;
            }
        } while (!flag);

        do {
            flag = false;
            try {
                s = JOptionPane.showInputDialog("Departamento no qual ocorreu o problema:");
                c.setDepartamento(s);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro Cancelado");
                return;
            }
        } while (!flag);

        try {
            c.setTipoServico(listaServico.requisitarServico());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Cadastro Cancelado");
            return;
        }

        try {
            c.setEquipamento(listaEquipamento.requisitarEquipamento());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Cadastro Cancelado");
            return;
        }

        do {
            flag = false;
            try {
                s = JOptionPane.showInputDialog("Descreva o problema apresentado:");
                c.setProblema(s);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro Cancelado");
                return;
            }
        } while (!flag);

        do {
            flag = false;
            try {
                s = JOptionPane.showInputDialog("Observacao sobre o equipamento/problema");
                c.setObservacao(s);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro Cancelado");
                return;
            }
        } while (!flag);

        c.setExecucaoFinal(null);
        c.setTecnicoResponsavel(null);

        do {
            flag = false;
            try {
                c.setSolucao("Ainda nao resolvido");
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } while (!flag);

        try {
            listaChamado.adicionar(c);
            JOptionPane.showMessageDialog(null, "Chamado cadastrado com sucesso.");
        } catch (Excecao ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void editar(ChamadoDAO listaChamado, ServicoDAO listaServico, EquipamentoDAO listaEquipamento, FuncionarioDAO listaFuncionario) {
        Chamado c = new Chamado();
        String str;
        boolean flag;
        DateFormat d = DateFormat.getDateInstance();

        do {
            flag = false;
            try {
                c.setId(Integer.valueOf(JOptionPane.showInputDialog("Digite o ID: ")));
                flag = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Edicao cancelada");
                return;
            }
        } while (!flag);

        for (Chamado cc : listaChamado.getLista()) {
            if (c.equals(cc)) {
                try {
                    do {
                        flag = false;
                        str = JOptionPane.showInputDialog("O chamado a ser editado foi finalizado?");
                        if (str.equals("Sim") || str.equals("sim")) {
                            c.setStatus('F');
                            flag = true;
                        } else {
                            if (str.equals("Nao") || str.equals("nao") || str.equals("Nao") || str.equals("nao")) {
                                c.setStatus('A');
                                flag = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Responda com 'sim' ou 'nao'.");
                            }
                        }
                    } while (!flag);

                    c.setId(cc.getId());
                    c.setDataAbertura(cc.getDataAbertura());
                    c.setExecucaoInicial(cc.getExecucaoInicial());
                    c.setSolicitante(cc.getSolicitante());
                    c.setDepartamento(cc.getDepartamento());

                    if (c.getStatus() == 'F') {
                        c.setTipoServico(cc.getTipoServico());
                        c.setEquipamento(cc.getEquipamento());
                        c.setProblema(cc.getProblema());
                        c.setObservacao(cc.getObservacao());
                    }
                } catch (Excecao ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }


                if (c.getStatus() == 'F') {
                    c.setExecucaoFinal(new Date());

                    try {
                        c.setTecnicoResponsavel(listaFuncionario.requisitarFuncionario());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Edicao Cancelada");
                        return;
                    }

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Solucao encontrada para o problema apresentado:");
                            c.setSolucao(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(null, "Edicao Cancelada");
                            return;
                        }
                    } while (!flag);
                }

                if (c.getStatus() == 'A') {
                    c.setTipoServico(listaServico.requisitarServico());
                    c.setEquipamento(listaEquipamento.requisitarEquipamento());

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Descreva o problema apresentado:");
                            c.setProblema(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(null, "Edicao Cancelada");
                            return;
                        }
                    } while (!flag);

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Observacao sobre o equipamento/problema");
                            c.setObservacao(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(null, "Edicao Cancelada");
                            return;
                        }
                    } while (!flag);

                    c.setExecucaoFinal(null);
                    c.setTecnicoResponsavel(null);

                    do {
                        flag = false;
                        try {
                            c.setSolucao("Ainda nao resolvido");
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } while (!flag);
                }
                try {
                    listaChamado.excluir(cc);
                    listaChamado.adicionar(c);
                    JOptionPane.showMessageDialog(null, "Chamado editado com sucesso.");
                    return;
                } catch (Excecao ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Chamado nao encontrado");
    }

    private static void remover(ChamadoDAO listaChamado) {
        Chamado c = new Chamado();

        try {
            c.setId(Integer.valueOf(JOptionPane.showInputDialog("Digite um codigo: ")));
            listaChamado.excluir(c);
            JOptionPane.showMessageDialog(null, "Chamado excluido com sucesso.");
        } catch (Excecao ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Remocao cancelada");
            return;
        }
    }

    private static void listar(ChamadoDAO listaChamado) {
        int contador, opc = -1;
        String aux;

        do {
            contador = 0;
            aux = JOptionPane.showInputDialog(
                    "Quais chamados deseja listar?\n\n"
                    + "1 - Abertos\n"
                    + "2 - Fechados\n"
                    + "3 - Todos\n"
                    + "===========\n"
                    + "0 - Voltar\n"
                    + "===========\n"
            );

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
                    for (Chamado cc : listaChamado.getLista()) {
                        if (cc.getStatus() == 'A') {
                            JOptionPane.showMessageDialog(null, cc);
                            contador++;
                        }
                    }
                    if (contador == 0) {
                        JOptionPane.showMessageDialog(null, "Nenhum Chamado Cadastrado.");
                    }
                    break;
                case 2:
                    for (Chamado cc : listaChamado.getLista()) {
                        if (cc.getStatus() == 'F') {
                            JOptionPane.showMessageDialog(null, cc);
                            contador++;
                        }
                    }
                    if (contador == 0) {
                        JOptionPane.showMessageDialog(null, "Nenhum Chamado Cadastrado!");
                    }
                    break;
                case 3:
                    for (Chamado cc : listaChamado.getLista()) {
                        JOptionPane.showMessageDialog(null, cc);
                        contador++;
                    }
                    if (contador == 0) {
                        JOptionPane.showMessageDialog(null, "Nenhum Chamado Cadastrado!");
                    }
                    break;
                case 0:
                break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao Invalida!");
                    break;
            }
        } while (opc != 0);
    }
}
