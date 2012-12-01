package util;

import java.util.Date;

import javax.swing.JOptionPane;

import dao.Usuario;

public class UsuarioUtil extends Usuario{

	private Date dataLogin;
	
	private static UsuarioUtil usuarioUtil= new UsuarioUtil();
	
	public static UsuarioUtil getInstance(){
		return usuarioUtil;		
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}
	
	public void usuarioLogado(Usuario usuario){
		Date data = new Date();
		setIdUsuario(usuario.getIdUsuario());
		setNome(usuario.getNome());
		setMatricula(usuario.getMatricula());
		setEmail(usuario.getEmail());
		setAdministrador(usuario.getAdministrador());
		setDataLogin(data);
	}

	public boolean tempoLogin(){
		Date dataAtual = new Date();
		long tempo = (dataAtual.getTime() - getDataLogin().getTime())/1000/60;
		if (tempo > 10 ){
			JOptionPane.showMessageDialog(null, "Sessão EXPIRADA!");
			return false;
		}else{
			setDataLogin(dataAtual);
			return true;
		}
	}
	
	public boolean ehAdministrador(){
		return getAdministrador();
	}
}
