package br.com.compasso.lambda.desafioCompasso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.compasso.lambda.desafioCompasso.models.Categoria;
import br.com.compasso.lambda.desafioCompasso.models.Filme;
import br.com.compasso.lambda.desafioCompasso.repositories.CategoriaRepository;
import br.com.compasso.lambda.desafioCompasso.repositories.FilmeRepository;

@SpringBootApplication
public class DesafioCompassoApplication implements CommandLineRunner{

	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioCompassoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Comédia");
		Categoria cat2 = new Categoria("Ficção");
		Categoria cat3 = new Categoria("Terror");
		Categoria cat4 = new Categoria("Suspense");
		Categoria cat5 = new Categoria("Aventura");
		Categoria cat6 = new Categoria("Ação");
		
		Filme f1 = new Filme("Ameaça Profunda", "Criaturas misteriosas aterrorizam os tripulantes de um laboratório subaquático a 11 mil metros de profundidade.");
		Filme f2 = new Filme("Resgate", "Resgate é um filme de suspense de ação americano de 2020");
		Filme f3 = new Filme("Eli", "Eli é um filme de terror americano de 2019, dirigido por Ciarán Foy com o elenco de Kelly Reilly e Sadie Sink");
		Filme f4 = new Filme("Tempestade: Planeta em Fúria", "A ocorrência cada vez mais frequente de eventos climáticos capazes de ameaçar a existência da humanidade faz com que seja criada uma extensa rede de satélites, ao redor de todo o planeta, de forma a controlar o próprio clima.");
		
		cat2.getFilmes().addAll(Arrays.asList(f1));
		cat3.getFilmes().addAll(Arrays.asList(f2, f3, f4));
		
		categoriaRepository.saveAll(Arrays.asList(cat2, cat3));
		
		
	}
	
	

}
