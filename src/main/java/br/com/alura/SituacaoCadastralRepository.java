package br.com.alura;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SituacaoCadastralRepository {

    private final List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia) {
        agencias.add(agencia);
    }

    public Agencia buscarPorId(String cnpj) {
        return agencias.stream().filter(agencia -> agencia.getCnpj().equals(cnpj)).toList().get(0);
    }
}
