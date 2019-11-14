package br.net.jassa.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.net.jassa.cursomc.domain.enums.TipoCliente;
import br.net.jassa.cursomc.dto.ClienteNewDTO;
import br.net.jassa.cursomc.resources.exception.FieldMessage;
import br.net.jassa.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
        // inclua os testes aqui, inserindo erros na lista
		if ((objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod())) && (!BR.isValidCpf(objDto.getCpfOuCnpj()))) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if ((objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod())) && (!BR.isValidCnpj(objDto.getCpfOuCnpj()))) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		// vai pegar todos os objetos de erros e incluir no contexto do framework
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			       .addPropertyNode(e.getFieldName())
				   .addConstraintViolation();
		}
		return list.isEmpty();
	}
}