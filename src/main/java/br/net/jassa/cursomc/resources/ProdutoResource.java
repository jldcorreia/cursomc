package br.net.jassa.cursomc.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.net.jassa.cursomc.domain.Produto;
import br.net.jassa.cursomc.dto.ProdutoDTO;
import br.net.jassa.cursomc.resources.utils.URL;
import br.net.jassa.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			                                           @RequestParam(value="nome", defaultValue="")           String nome,
			                                           @RequestParam(value="categorias", defaultValue="")     String categorias,
			                                           @RequestParam(value="page", defaultValue="0")          Integer page, 
			                                           @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			                                           @RequestParam(value="orderBy", defaultValue="nome")    String orderBy, 
			                                           @RequestParam(value="direction", defaultValue="ASC")   String direction) {
		/* quando um parametro string vem com espaços ele codifica os espaços em branço. P. ex.
		 * TV LED --> TV%LED
		 */
		String nomeDecoded = URL.decodeParame(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		// abaixo vc pode usar o search ou o search2 só para ver como funciona a query methods name do spring
		Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok()
				             .body(listDto);
	}		
	
}
