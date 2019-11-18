package br.net.jassa.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.net.jassa.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
	/* é uma interface, mas o spring automaticamente cria, basta ver na documentação do spring data jpa - reference documentation
	 * item 4.3 - query methods --> supported keywords inside method names
	 *  */
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
