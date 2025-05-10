package br.com.fiap.contatos.service;

import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;


    public Contato gravar(Contato contato) {
        return contatoRepository.save(contato);
    }

    public Contato buscarContatoPorId(Long id) {

        Optional<Contato> contatoOptional = contatoRepository.findById(id);

        if (contatoOptional.isPresent()) {
            return contatoOptional.get();
        }else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public List<Contato> listarTodosContatos(){
        return contatoRepository.findAll();
    }

    public void excluir(Long id) {
        Optional<Contato> contatoOptional = contatoRepository.findById(id);
        if (contatoOptional.isPresent()) {
            contatoRepository.delete(contatoOptional.get());
        }else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public List<Contato> mostrarAniversariantes(LocalDate dataInicial, LocalDate dataFinal) {
        return contatoRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
    }

    public Contato atualizar(Contato contato) {
        Optional<Contato> contatoOptional = contatoRepository.findById(contato.getId());

        if (contatoOptional.isPresent()) {
            contatoRepository.delete(contatoOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
        return contato;
    }

}

