package br.com.alura;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/situacao-cadastral")
public class SituacaoCadastralController {

    private final SituacaoCadastralRepository situacaoCadastralRepository;

    SituacaoCadastralController(SituacaoCadastralRepository situacaoCadastralRepository) {
        this.situacaoCadastralRepository = situacaoCadastralRepository;
    }

    @POST
    @Transactional
    public void cadastrar(Agencia agencia) {
        this.situacaoCadastralRepository.persist(agencia);
    }

    @GET
    @Path("{cnpj}")
    public Agencia buscarPorCnpj(String cnpj) {
        return this.situacaoCadastralRepository.findByCnpj(cnpj);
    }
}
