package Teste;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Usuario user = new Usuario("jonathan", "12345");
		TelaLogin tela = new TelaLogin();
		tela.cadastrarUsuario(user);
		tela.efetuarLogin("jonathan", "12345");
		System.out.println(tela.getSession());
	}

}

class TelaLogin {
	ControladorLogin controlador;
	
	public TelaLogin() {
		controlador = new ControladorLogin();
	}
	
	public void cadastrarUsuario(Usuario user) {
		controlador.cadastrarUsuario(user);
	}
	
	public void efetuarLogin(String login, String senha) {
		controlador.efetuarLogin(login, senha);
	}
	
	public String getSession() {
		return controlador.getSession();
	}
}

class ControladorLogin implements CadastroUsuarios{
	
	private String sessao;
	private HashMap<String, String> usuarios;
	
	public ControladorLogin() {
		this.sessao = "Não efetuada";
		this.usuarios = new HashMap<>();
	}
	
	public boolean efetuarLogin(String login, String senha) {
		if(checarLogin(login, senha)) {
			registrarSessao();
			System.out.println("Usuário " + login + " logado com sucesso!");
			return true;
		} else {
			System.out.println("Falha ao tentar logar o usuário " + login + ".");
			return false;
		}
	}

	@Override
	public boolean checarLogin(String login, String senha) {
		// TODO Auto-generated method stub
		if (usuarios.containsKey(login)) {
			if (usuarios.get(login).equals(senha)) {
				registrarSessao();
				return true;
			}
		}
		return false;
	}
	
	public void registrarSessao() {
		sessao = GregorianCalendar.getInstance().getTime().toString();
	}
	
	public String getSession() {
		return this.sessao;
	}

	@Override
	public boolean cadastrarUsuario(Usuario user) {
		if (user == null) {
			System.out.println("Falha ao cadastrar usuário. (método ControladorLogin:cadastrarUsuario");
			return false;
		} else {
			if (user.getLogin() == null || user.getLogin().length() < 5 ||
					user.getSenha() == null || user.getSenha().length() < 5) {
				System.out.println("Falha ao cadastrar usuário. (método ControladorLogin:cadastrarUsuario");
				return false;
			} else {
				usuarios.put(user.getLogin(), user.getSenha());
				return true;
			}
		}
	}
}

interface CadastroUsuarios {
	public boolean checarLogin(String login, String senha);
	public boolean cadastrarUsuario(Usuario user);
}

class Usuario {
	private String login;
	private String senha;
	
	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}


