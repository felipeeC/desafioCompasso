package br.com.compasso.lambda.desafioCompasso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.lambda.desafioCompasso.dtos.PessoaDto;
import br.com.compasso.lambda.desafioCompasso.models.Pessoa;
import br.com.compasso.lambda.desafioCompasso.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	//Métodoss

	@Transactional(readOnly = true)
	public Page<PessoaDto> retornaTodas(Pageable paginacao){
		Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
		
		return PessoaDto.converter(pessoas);
	}

	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}


//	public void removeById(int idPessoa) {
//		
//		Pessoa pessoaRemovida = this.findById(idPessoa);
//		
//		if(pessoaRemovida != null) {
//			pessoas.remove(pessoaRemovida);
//			System.out.println("Pessoa removida com sucesso!");
//		} else {
//			System.out.println("Não foi encotrada nenhuma pessoa com esse id!");
//		}
//
//	}
	
//	public void editaNomePessoa(int idPessoa, String novoNome) {
//		
//		if(novoNome != null && novoNome != "") {			
//			Pessoa pessoaEditada = this.findById(idPessoa);
//			
//			if(pessoaEditada != null) {
//				pessoaEditada.setNome(novoNome);
//				System.out.println("Pessoa editada com sucesso!");
//			} else {
//				System.out.println("Pessoa não encontrada!");
//			}
//		} else {
//			System.out.println("Nome inválido!");
//		}
//		
//		
//	}
	
//	public void editaIdadePessoa(int idPessoa, int novaIdade) {
//		if(novaIdade > 0) {
//			Pessoa pessoaEditada = this.findById(idPessoa);
//			if(pessoaEditada != null) {
//				pessoaEditada.setIdade(novaIdade);
//				System.out.println("Pessoa editada com sucesso");
//			} else {
//				System.out.println("Pessoa não encontrada!");
//			}
//		} else {
//			System.out.println("Idade inválida!");
//		}
//		
//		
//	}

//	public void adiciona(String nome, int idade) {
//		Pessoa novaPessoa = new Pessoa();
//		
//		if(pessoas.contains(novaPessoa)) {
//			System.out.println("Pessoa já existe");
//		} else {
//			pessoas.add(novaPessoa);
//			System.out.println("Pessoa adicionada com sucesso!");
//		}
//	}
	
//	public Pessoa findById(int idPessoa) {
//		Pessoa pessoaEncontrada = null;
//		
//		for (Pessoa pessoa : pessoas) {
//			if(pessoa.getId() == idPessoa) {
//				pessoaEncontrada = pessoa;
//			}
//		}
//		
//		return pessoaEncontrada;
//	}
	
//	public List<Pessoa> imprimeByName(String nome) {
//		List<Pessoa> pessoasEncontradas = new ArrayList<>();
//		
//		for (Pessoa pessoa : pessoas) {
//			if(pessoa.getNome() == nome) {
//				pessoasEncontradas.add(pessoa);
//			}
//		}
//		
//		return pessoasEncontradas;
//	}
	
//	public boolean verificaById(int id) {
//		for (Pessoa pessoa : pessoas) {
//			if(pessoa.getId() == id) return true;
//		}
//		return false;
//	}
}
