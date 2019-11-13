package br.net.jassa.cursomc.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.jassa.cursomc.domain.Categoria;
import br.net.jassa.cursomc.dto.CategoriaDTO;
import br.net.jassa.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list = service.findAll();
		/* Padrão DTO -> cria uma outra classe para apenas trabalhar com os dados da classe original
		 * P. Ex. Categoria traz os produtos da categoria, mas não queremos, então criamos outra classe CategoriaDTO
		 * apenas com os dados que queremos mostrar e lá na classe CategoriaDTO o construtor recebe o Categoria */
		List<CategoriaDTO> listDto = list.stream()
				                         .map(obj -> new CategoriaDTO(obj)) 
				                         .collect(Collectors.toList());
		return ResponseEntity.ok()
				             .body(listDto);
	}	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder
				       .fromCurrentRequest()
				       .path("/{id}")
				       .buildAndExpand(obj.getId())
				       .toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	

}
