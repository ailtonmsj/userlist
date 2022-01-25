package br.com.ailtonmsj.dto;

import java.util.Map;

import br.com.ailtonmsj.service.AbstractUsuarioService;
import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RegisterForReflection
public class Usuario {
	
	public Usuario() {	}
	
	public Usuario(
			String nome,
			String sobrenome,
			Integer idade,
			String pais,
			Boolean status) {
		
		this.nome = nome ;
	    this.sobrenome = sobrenome;
	    this.idade = idade;
	    this.pais = pais;
	    this.status = status;
	}
	
    public static Usuario from(Map<String, AttributeValue> item) {
        Usuario usuario = new Usuario();
        if (item != null && !item.isEmpty()) {
            usuario.setNome(item.get(AbstractUsuarioService.USUARIO_NOME_COL).s());
            usuario.setSobrenome(item.get(AbstractUsuarioService.USUARIO_SOBRENOME_COL).s());
            usuario.setIdade(item.get(AbstractUsuarioService.USUARIO_IDADE_COL).n());
            usuario.setPais(item.get(AbstractUsuarioService.USUARIO_PAIS_COL).s());
            
            if(item.get(AbstractUsuarioService.USUARIO_STATUS_COL) != null) {
            	usuario.setStatus(item.get(AbstractUsuarioService.USUARIO_STATUS_COL).bool());
            }
        }
        return usuario;
    }

    private String nome;
    private String sobrenome;
    private Integer idade;
    private String pais;
    private Boolean status;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public void setIdade(String idade) {
		if(idade != null)
			this.idade = Integer.parseInt(idade);
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idade == null) ? 0 : idade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idade == null) {
			if (other.idade != null)
				return false;
		} else if (!idade.equals(other.idade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", sobrenome=" + sobrenome + ", idade=" + idade + ", pais=" + pais
				+ ", status=" + status + "]";
	}
}