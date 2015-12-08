package persistencia;

import excecao.Excecao;
import java.util.ArrayList;
import java.util.List;
import modelo.Endereco;

public class EnderecoDAO {

    private List<Endereco> lista = new ArrayList<Endereco>();

    public void adicionar(Endereco endereco) throws Excecao {
        for (Endereco e : lista) {
            if (e.equals(endereco)) {
                throw new Excecao("Endereco Ja Cadastrado.");
            }
        }
        lista.add(endereco);
    }

    public void editar(Endereco endereco) {
        for (Endereco e : lista) {
            if (e.equals(endereco)) {
                lista.remove(e);
                lista.add(endereco);
                return;
            }
        }
    }

    public void excluir(Endereco endereco) throws Excecao {
        for (Endereco e : lista) {
            if (e.equals(endereco)) {
                lista.remove(endereco);
                return;
            }
        }
        throw new Excecao("Endereco Nao Encontrado.");
    }
}
