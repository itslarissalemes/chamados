package br.com.sistemas.chamados.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sistemas.chamados.entity.Categoria;
import br.com.sistemas.chamados.entity.Chamado;
import br.com.sistemas.chamados.entity.Cliente;
import br.com.sistemas.chamados.entity.Prioridade;
import br.com.sistemas.chamados.entity.StatusChamado;
import br.com.sistemas.chamados.repository.CategoriaRepository;
import br.com.sistemas.chamados.repository.ChamadoRepository;
import br.com.sistemas.chamados.repository.ClienteRepository;

@Controller
@RequestMapping("/web")
public class ChamadoWebController {

    private final ChamadoRepository chamadoRepository;
    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;

    public ChamadoWebController(ChamadoRepository chamadoRepository,
                                ClienteRepository clienteRepository,
                                CategoriaRepository categoriaRepository) {
        this.chamadoRepository = chamadoRepository;
        this.clienteRepository = clienteRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Exibe o Dashboard com os contadores e a tabela de chamados
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalChamados = chamadoRepository.count();

        long abertos = chamadoRepository.findAll().stream()
                .filter(c -> c.getStatus() != null && c.getStatus().name().equals("ABERTO"))
                .count();

        long fechados = totalChamados - abertos;

        model.addAttribute("totais", totalChamados);
        model.addAttribute("abertos", abertos);
        model.addAttribute("fechados", fechados);
        model.addAttribute("chamados", chamadoRepository.findAll());

        return "dashboard";
    }

    // Exibe o formulário de novo chamado
    @GetMapping("/chamados/novo")
    public String novoChamadoForm() {
        return "chamado-form";
    }

    // Recebe os dados do formulário e guarda na base de dados
    @PostMapping("/chamados/novo")
    public String salvarChamado(@RequestParam String titulo,
                                @RequestParam Long clienteId,
                                @RequestParam Long categoriaId,
                                @RequestParam String descricao) {
        try {
            Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
            Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);

            if (cliente == null || categoria == null) {
                return "redirect:/web/chamados/novo?erro=id_invalido";
            }

            Chamado chamado = new Chamado();
            chamado.setTitulo(titulo);
            chamado.setDescricao(descricao);
            chamado.setStatus(StatusChamado.ABERTO);
            chamado.setPrioridade(Prioridade.MEDIA);
            chamado.setDataAbertura(LocalDateTime.now());
            chamado.setCliente(cliente);
            chamado.setCategoria(categoria);

            chamadoRepository.save(chamado);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/web/dashboard";
    }

    // Exibe a tela para alterar o status de um chamado específico
    @GetMapping("/chamados/{id}/status")
    public String alterarStatusForm(@PathVariable Long id, Model model) {
        Chamado chamado = chamadoRepository.findById(id).orElse(null);
        if (chamado == null) {
            return "redirect:/web/dashboard";
        }
        model.addAttribute("chamado", chamado);
        model.addAttribute("statusList", StatusChamado.values());
        return "chamado-status";
    }

    // Recebe o novo status e atualiza na base de dados
    @PostMapping("/chamados/{id}/status")
    public String atualizarStatus(@PathVariable Long id, @RequestParam StatusChamado status) {
        try {
            Chamado chamado = chamadoRepository.findById(id).orElse(null);
            if (chamado != null) {
                chamado.setStatus(status);

                if (status == StatusChamado.FECHADO) {
                    chamado.setDataFechamento(LocalDateTime.now());
                } else {
                    chamado.setDataFechamento(null);
                }

                chamadoRepository.save(chamado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/web/dashboard";
    }
}