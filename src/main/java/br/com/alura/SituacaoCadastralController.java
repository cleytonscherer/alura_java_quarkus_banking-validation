package br.com.alura;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestResponse;

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
    public RestResponse<Agencia> buscarPorCnpj(String cnpj) {
        Agencia agencia = this.situacaoCadastralRepository.findByCnpj(cnpj);
        if (agencia != null) {
            return RestResponse.ok(agencia);
        } throw new RuntimeException();
    }
}
