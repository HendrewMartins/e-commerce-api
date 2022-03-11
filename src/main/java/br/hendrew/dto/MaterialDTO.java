package br.hendrew.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.hendrew.entity.Material;
import br.hendrew.entity.UnidadeMedida;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "MaterialDTO", description = "DTO para Criar um novo Material")
public class MaterialDTO {
    
        @Schema(title = "descricao", required = true)
		private String descricao;

		@Schema(title = "quantidade", required = true)
		private Double quantidade;

		@Schema(title = "unidadeMedida", required = true)
		private UnidadeMedida unidadeMedida;

		@Schema(title = "valor", required = true)
		private Double valor;


		public Material toMaterial() {
			Material material = new Material();
            material.setDescricao(getDescricao());
            material.setQuantidade(getQuantidade());
            material.setUnidadeMedida(getUnidadeMedida());
            material.setValor(getValor());
			return material;
		}
}
