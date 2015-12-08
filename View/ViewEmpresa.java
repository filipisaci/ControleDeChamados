package view;

import excecao.Excecao;
import javax.swing.JOptionPane;
import modelo.Empresa;
import modelo.Endereco;
import persistencia.EmpresaDAO;

public class ViewEmpresa {

    public static void menu(EmpresaDAO listaEmpresa) throws Excecao {
        int opc = -1;
        String aux = "";
        do {
            aux = JOptionPane.showInputDialog(
                    "++++++++++++++++++++\n"
                    + "+          MENU EMPRESA\n"
                    + "++++++++++++++++++++\n"
                    + "+ 1 - Cadastrar\n"
                    + "+ 2 - Editar\n"
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
                    cadastrar(listaEmpresa);
                    break;
                case 2:
                    editar(listaEmpresa);
                    break;
                case 3:
                    remover(listaEmpresa);
                    break;
                case 4:
                    listar(listaEmpresa);
                    break;
                case 0:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao Invalida");
            }
        } while (opc != 0);
    }

    private static void cadastrar(EmpresaDAO listaEmpresa) {
        String str;
        boolean flag;

        Empresa e = new Empresa();
        Endereco endereco = new Endereco();

        e.setId(listaEmpresa.getProximoId());
        e.setStatus('A');

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite um CNPJ:");
                e.setCnpj(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                return;
            }
        } while (!flag);

        str = JOptionPane.showInputDialog("Digite a Razao Social:");
        if (str == null) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado");
            return;
        } else {
            e.setRazaoSocial(str);
        }

        str = JOptionPane.showInputDialog("Digite o Nome Fantasia:");
        if (str == null) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado");
            return;
        } else {
            e.setNome(str);
        }

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite a Rua: ");
                endereco.setRua(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                return;
            }
        } while (!flag);

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite o Numero: ");
                endereco.setNumero(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                return;
            }
        } while (!flag);

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite o Bairro: ");
                endereco.setBairro(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                return;
            }
        } while (!flag);

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite o Cidade: ");
                endereco.setCidade(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                return;
            }
        } while (!flag);

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite o Estado: ");
                endereco.setEstado(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                return;
            }
        } while (!flag);

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite o Nome da Pessoa de Contato:");
                e.setContato(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                return;
            }
        } while (!flag);

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite o telefone:");
                e.setTelefone(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado");
                return;
            }
        } while (!flag);

        try {
            e.setEndereco(endereco);
            listaEmpresa.adicionar(e);
            JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso");
        } catch (Excecao ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Escolha uma opcao valida");
        }
    }

    private static void editar(EmpresaDAO listaEmpresa) throws Excecao {
        String str, nomeAux;
        boolean flag;

        Empresa e = new Empresa();
        Endereco endereco = new Endereco();


        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite um CNPJ: ");
                e.setCnpj(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, "CNPJ Invalido");
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Edicao cancelada");
                return;
            }
        } while (!flag);

        for (Empresa empresa : listaEmpresa.getLista()) {
            if (e.equals(empresa)) {
                try {
                    e.setId(empresa.getId());
                    e.setStatus('A');

                    nomeAux = empresa.getNome();
                    do {
                        flag = false;
                        str = JOptionPane.showInputDialog("A empresa '" + nomeAux + "' continua ativa?");
                        if (str.equals("Sim") || str.equals("sim")) {
                            e.setStatus('A');
                            flag = true;
                        } else {
                            if (str.equals("Nao") || str.equals("nao") || str.equals("Nao") || str.equals("nao")) {
                                e.setStatus('I');
                                flag = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Responda com 'sim' ou 'nao'.");
                            }
                        }
                    } while (!flag);

                    str = JOptionPane.showInputDialog("Digite a Razao Social:");
                    if (str == null) {
                        JOptionPane.showMessageDialog(null, "Edicao cancelada");
                        return;
                    } else {
                        e.setRazaoSocial(str);
                    }

                    str = JOptionPane.showInputDialog("Digite o Nome Fantasia:");
                    if (str == null) {
                        JOptionPane.showMessageDialog(null, "Edicao cancelada");
                        return;
                    } else {
                        e.setNome(str);
                    }

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Digite a Rua: ");
                            endereco.setRua(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } while (!flag);

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Digite o Numero: ");
                            endereco.setNumero(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } while (!flag);

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Digite o Bairro: ");
                            endereco.setBairro(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } while (!flag);

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Digite a Cidade: ");
                            endereco.setCidade(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } while (!flag);

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Digite o Estado: ");
                            endereco.setEstado(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } while (!flag);

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Digite o Nome da Pessoa de Contato:");
                            e.setContato(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } while (!flag);

                    do {
                        flag = false;
                        try {
                            str = JOptionPane.showInputDialog("Digite o telefone:");
                            e.setTelefone(str);
                            flag = true;
                        } catch (Excecao ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    } while (!flag);

                    listaEmpresa.excluir(empresa);
                    e.setEndereco(endereco);
                    listaEmpresa.adicionar(e);
                    JOptionPane.showMessageDialog(null, "Empresa editada com sucesso.");
                    return;

                } catch (Excecao ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Edicao Cancelada");
                    return;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Edicao Cancelada");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Empresa nao encontrada.");
    }

    private static void remover(EmpresaDAO listaEmpresa) throws Excecao {
        Empresa e = new Empresa();
        boolean flag;
        String str;

        do {
            flag = false;
            try {
                str = JOptionPane.showInputDialog("Digite um CNPJ: ");
                e.setCnpj(str);
                flag = true;
            } catch (Excecao ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Remocao cancelada");
                return;
            }
        } while (!flag);

        for (Empresa ee : listaEmpresa.getLista()) {
            if (e.equals(ee)) {
                try {
                    listaEmpresa.excluir(ee);
                    JOptionPane.showMessageDialog(null, "Empresa removida com sucesso.");
                    break;
                } catch (Excecao ex) {
                    JOptionPane.showMessageDialog(null, "Empresa nao encontrada");
                }
            }
        }
    }

    private static void listar(EmpresaDAO listaEmpresa) {
        int contador = 0;
        for (Empresa ee : listaEmpresa.getLista()) {
            JOptionPane.showMessageDialog(null, ee);
            contador++;
        }
        if (contador == 0) {
            JOptionPane.showMessageDialog(null, "Nenhuma Empresa Cadastrada");
        }
    }
}
