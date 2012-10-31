package dao;
// default package


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dao.Servico;

/**
 * Fornecedor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fornecedor", catalog = "frotatj")
public class Fornecedor implements java.io.Serializable {

	// Fields

	private Integer idfornecedor;
	private String nome;
	private String cnpj;
	private String fone1;
	private String email;
	private String fone2;
	private Set<Servico> servicos = new HashSet<Servico>(0);

	// Constructors

	/** default constructor */
	public Fornecedor() {
	}

	/** full constructor */
	public Fornecedor(String nome, String cnpj, String fone1, String email,
			String fone2, Set<Servico> servicos) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.fone1 = fone1;
		this.email = email;
		this.fone2 = fone2;
		this.servicos = servicos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idfornecedor", unique = true, nullable = false)
	public Integer getIdfornecedor() {
		return this.idfornecedor;
	}

	public void setIdfornecedor(Integer idfornecedor) {
		this.idfornecedor = idfornecedor;
	}

	@Column(name = "nome", length = 200)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cnpj", length = 200)
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "fone1", length = 30)
	public String getFone1() {
		return this.fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fone2", length = 100)
	public String getFone2() {
		return this.fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fornecedor")
	public Set<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}
	public String toString()
	{
		return this.getNome();
	}

}
