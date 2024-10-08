package br.com.alura;

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
    public void cadastrar(Agencia agencia) {
        this.situacaoCadastralRepository.cadastrar(agencia);
    }

    @GET
    @Path("{cnpj}")
    public Agencia buscarPorCnpj(String cnpj) {
        return this.situacaoCadastralRepository.buscarPorId(cnpj);
    }
}
