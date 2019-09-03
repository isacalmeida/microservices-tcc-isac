package br.edu.unoesc.uiservice.controller.pessoaservice;

import br.edu.unoesc.uiservice.model.pessoaservice.Cidade;
import br.edu.unoesc.uiservice.model.pessoaservice.Estado;
import br.edu.unoesc.uiservice.proxy.PessoaServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cidades")
public class CidadeController {

    // == fields ==
    private PessoaServiceProxy pessoaServiceProxy;

    // == constructors ==
    @Autowired
    public CidadeController(PessoaServiceProxy pessoaServiceProxy){
        this.pessoaServiceProxy = pessoaServiceProxy;
    }

    // == public methods ==
    @GetMapping
    public ModelAndView home(){
        List<Cidade> cidades = pessoaServiceProxy.getAllCidade();
        Integer port = pessoaServiceProxy.getPortCidade();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cidades", cidades);
        modelAndView.addObject("port", port);
        modelAndView.setViewName("cidades/index");
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView novo(){
        List<Estado> estados = pessoaServiceProxy.getAllEstado();
        Integer port = pessoaServiceProxy.getPortEstadoCidade(estados.get(0).getId());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cidade", new Cidade());
        modelAndView.addObject("estados", estados);
        modelAndView.addObject("port", port);
        modelAndView.setViewName("cidades/novo");
        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView getOne(@PathVariable Long id){
        List<Estado> estados = pessoaServiceProxy.getAllEstado();
        Cidade cidade = pessoaServiceProxy.getOneCidade(id);
        Integer port = pessoaServiceProxy.getPortCidade();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cidade", cidade);
        modelAndView.addObject("estados", estados);
        modelAndView.addObject("port", port);
        modelAndView.setViewName("cidades/editar");
        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public ModelAndView excluir(@PathVariable Long id) {
        pessoaServiceProxy.deleteCidade(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/cidades");
        return modelAndView;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute Cidade cidade){
        Cidade cidadeCreated = pessoaServiceProxy.createEstadoCidade(cidade.getEstado().getId(), cidade);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/cidades");
        return modelAndView;
    }

    @PostMapping("/atualizar")
    public ModelAndView atualizar(@ModelAttribute Cidade cidade) {
        Cidade cidadeUpdated = pessoaServiceProxy.updateEstadoCidade(cidade.getEstado().getId(), cidade.getId(), cidade);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/cidades");
        return modelAndView;
    }
}