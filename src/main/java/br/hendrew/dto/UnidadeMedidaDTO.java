package br.hendrew.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.hendrew.entity.UnidadeMedida;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "UnidadeMedidaDTO", description = "DTO para Criar um novo Unidade")
public class UnidadeMedidaDTO {

	@Schema(title = "descricao", required = true)
	private String descricao;

	@Schema(title = "sigla", required = true)
	private String sigla;


	public UnidadeMedida toUnidade() {
		UnidadeMedida unidadeMedida = new UnidadeMedida();
		unidadeMedida.setDescricao(getDescricao());
		unidadeMedida.setSigla(getSigla());
		return unidadeMedida;
	}
}
