package mb;

import java.sql.SQLException;
import java.util.List;

import dao.Usuario;
import dao.UsuarioDAO;

public class MBUsuario {
	private static MBUsuario usuarioMB = new MBUsuario();
		
	private MBUsuario(){	
		
	}	
	public static MBUsuario getInstance(){
		return usuarioMB;		
	}
	
	public String inserir(Usuario usuario) {
		
		String retorno = "Cadastro inserido.";
		UsuarioDAO daoUsuario = UsuarioDAO.getInstance();
		try {
			daoUsuario.save(usuario);
			
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public String editar(Usuario usuario) {
		String retorno = "Cadastro alterado.";
		UsuarioDAO daoUsuario = UsuarioDAO.getInstance();
		try {
			daoUsuario.update(usuario);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	

	public String apagar(Usuario usuario) {
		String retorno = "Cadastro removido.";
		UsuarioDAO daoUsuario = UsuarioDAO.getInstance();
		try {
			daoUsuario.delete(usuario);
		} catch (Exception e) {
			retorno = "erro";
		}
		return retorno;
	}
	
	public Usuario retornarUsuario(int id) {
		UsuarioDAO daoUsuario = UsuarioDAO.getInstance();
		return daoUsuario.findById(id);
	}

	public List<Usuario> listarUsuarios() throws ClassNotFoundException, SQLException{
		UsuarioDAO daoUsuario = UsuarioDAO.getInstance();
		return daoUsuario.findAll();
	}
}