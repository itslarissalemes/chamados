package br.com.sistemas.chamados.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.sistemas.chamados.repository.ChamadoRepository;
import br.com.sistemas.chamados.repository.CategoriaRepository;
import br.com.sistemas.chamados.repository.ClienteRepository;
import br.com.sistemas.chamados.repository.TecnicoRepository;

@Controller
public class WebViewController {

    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final TecnicoRepository tecnicoRepository;
    private final ChamadoRepository chamadoRepository;

    public WebViewController(ClienteRepository clienteRepository, CategoriaRepository categoriaRepository,
                             TecnicoRepository tecnicoRepository, ChamadoRepository chamadoRepository) {
        this.clienteRepository = clienteRepository;
        this.categoriaRepository = categoriaRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.chamadoRepository = chamadoRepository;
    }

    @GetMapping("/web/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalChamados", chamadoRepository.count());
        return "dashboard";
    }

    @GetMapping("/web/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @GetMapping("/web/categorias")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categorias";
    }

    @GetMapping("/web/tecnicos")
    public String listarTecnicos(Model model) {
        model.addAttribute("tecnicos", tecnicoRepository.findAll());
        return "tecnicos";
    }

    @GetMapping("/web/chamados")
    public String listarChamados(Model model) {
        model.addAttribute("chamados", chamadoRepository.findAll());
        return "chamados";
    }
}