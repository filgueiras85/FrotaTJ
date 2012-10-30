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

import dao.Usuario;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuario", catalog = "frotatj")
public class Usuario implements java.io.Serializable {

	// Fields

	private Integer idUsuario;
	private String nome;
	private String matricula;
	private char[] senha;
	private String email;
	private Boolean administrador;
	private Set<Servico> servicos = new HashSet<Servico>(0);

	// Constructors

	/** default constructor */
	public Usuario() {
	}
	public Usuario(Integer idUsuario, String nome, String matricula, char[] senha, String email, Boolean administrador){
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.email = email;
		this.administrador = administrador;
	}
	/** full constructor */
	public Usuario(String nome, String matricula, char[] senha, String email,
			Boolean administrador, Set<Servico> servicos) {
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.email = email;
		this.administrador = administrador;
		this.servicos = servicos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idusuario", unique = true, nullable = false)
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "nome", length = 100)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "matricula", length = 50)
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Column(name = "senha", length = 6)
	public char[] getSenha() {
		return this.senha;
	}

	public void setSenha(char[] senha) {
		this.senha = senha;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "administrador")
	public Boolean getAdministrador() {
		return this.administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(Set<Servico> servicos) {
		this.servicos = servicos;
	}

}
