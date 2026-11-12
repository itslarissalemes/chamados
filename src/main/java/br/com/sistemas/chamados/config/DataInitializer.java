package br.com.sistemas.chamados.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.sistemas.chamados.entity.Categoria;
import br.com.sistemas.chamados.entity.Cliente;
import br.com.sistemas.chamados.entity.Perfil;
import br.com.sistemas.chamados.entity.Tecnico;
import br.com.sistemas.chamados.repository.CategoriaRepository;
import br.com.sistemas.chamados.repository.ClienteRepository;
import br.com.sistemas.chamados.repository.TecnicoRepository;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final TecnicoRepository tecnicoRepository;

    public DataInitializer(ClienteRepository clienteRepository, CategoriaRepository categoriaRepository, TecnicoRepository tecnicoRepository) {
        this.clienteRepository = clienteRepository;
        this.categoriaRepository = categoriaRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            categoriaRepository.save(new Categoria("Hardware", "Problemas físicos com equipamentos"));
            categoriaRepository.save(new Categoria("Redes", "Problemas de conectividade e internet"));
        }
        if (clienteRepository.count() == 0) {
            clienteRepository.save(new Cliente("Empresa X", "contato@empresax.com", "999999999"));
        }
        if (tecnicoRepository.count() == 0) {
            tecnicoRepository.save(new Tecnico("Admin Sistema", "admin@sistema.com", Perfil.ADMIN));
            tecnicoRepository.save(new Tecnico("João Suporte", "joao@suporte.com", Perfil.TECNICO));
        }
    }
}