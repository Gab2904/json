package br.usjt.usjt_ccp3anmca_rest_json.model.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.usjt_ccp3anmca_rest_json.model.Cidade;
import br.usjt.usjt_ccp3anmca_rest_json.model.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@GetMapping("/lista")
	public List <Cidade> todasAsCidades() {
		return cidadeRepo.findAll();

	}
	
	@PostMapping ("/salvar")
	//@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<Cidade> salvar (@RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade c = cidadeRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentServletMapping().path("/cidades/{id}").
				buildAndExpand(c.getId()).toUri();
		//response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(c);
	}
	
	@GetMapping ("/{id}")
	public Cidade buscaPeloId(@PathVariable Long id) {
		return cidadeRepo.getOne(id);
	}
	
	@GetMapping ("/{Letra}")
	public Cidade buscaLetra(@PathVariable Long letra) {
		return cidadeRepo.getOne(letra);
	}
	
	
	@GetMapping ("/{latitude}")
	public Cidade buscaLatitude(@PathVariable Long latitude) {
		return cidadeRepo.getOne(latitude);
	}
	
	
	
}



