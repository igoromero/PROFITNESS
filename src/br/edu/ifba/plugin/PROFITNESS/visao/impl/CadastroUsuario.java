package br.edu.ifba.plugin.PROFITNESS.visao.impl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.PROFITNESS.controle.ControleUsuario;
import br.edu.ifba.plugin.PROFITNESS.modelo.ModeloUsuario;
import br.edu.ifba.plugin.PROFITNESS.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.PROFITNESS.visao.ICadastroUsuario;

@ManagedBean(name = "cadusuario")
@ViewScoped
public class CadastroUsuario implements ICadastroUsuario {
	private static final String ERRO_CPF_JA_CADASTRADO= "Cpf já cadastrado";
	private static final String ERRO_CPF_NAO_INFORMADO = "Cpf deve ser Informado";
	private static final String ERRO_RG_NAO_INFORMADO = "RG deve ser Informado";
	private static final String ERRO_Nome_NAO_INFORMADO = "Nome deve ser Informado";
	private static final String ERRO_SEXO_NAO_INFORMADO = "Sexo deve ser Informado";
	private static final String ERRO_TEL_NAO_INFORMADO = "Telefone deve ser Informado";
	private static final String ERRO_DATA_NAO_INFORMADO = "Data de Nascimento deve ser Informada";
	private static final String ERRO_LOGIN_NAO_INFORMADO = "Login deve ser Informado";
	private static final String ERRO_SENHA_NAO_INFORMADO = "Senha deve ser Informada";
	private static final String ERRO_SENHA_NAO_CONFERE = "As Senhas não Conferem";
	private static final String ERRO_SENHA_CURTA = "Senha muito curta";
	private static final String ERRO_CARGO_NAO_INFORMADO = "Cargo deve ser informado";
	
	public boolean gravar;
	public boolean gravado = false;

	private String id = "";
	private Usuario usuario = new Usuario();

	public CadastroUsuario() {
		ExternalContext contexto = FacesContext.getCurrentInstance()
				.getExternalContext();
		id = (String) contexto.getSessionMap().get("idUsuario");

		if ((id != null) && (!id.isEmpty())) {
			recuperarUsuario();
		}
	}

	private void recuperarUsuario() {
		ModeloUsuario modelo = new ModeloUsuario();
		ControleUsuario controle = new ControleUsuario();

		controle.setCadastroUsuario(this);
		controle.setModeloUsuario(modelo);

		controle.pesquisarParaCadastro();
	}

	@Override
	public int getId() {
		int iid = 0;

		if ((id != null) && (!id.isEmpty())) {
			iid = Integer.parseInt(id);
		}

		return iid;
	}

	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public void atualizarUsuarioEncontrado(Usuario usuario) {
		this.usuario = usuario;
	}

	public void gravar() {
		gravado = false;
		gravar = true;	
		
		if ((usuario.getCpf() == null) || (usuario.getCpf().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:cpf",new FacesMessage(ERRO_CPF_NAO_INFORMADO,ERRO_CPF_NAO_INFORMADO));
			gravar = false;
		}
		if((usuario.getRg() == null) || (usuario.getRg().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:rg", new FacesMessage(ERRO_RG_NAO_INFORMADO, ERRO_RG_NAO_INFORMADO));
			gravar = false;
		}
		if((usuario.getNome() == null) || (usuario.getNome().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:nome", new FacesMessage(ERRO_Nome_NAO_INFORMADO, ERRO_Nome_NAO_INFORMADO));
			gravar = false;
		}
		if((usuario.getSexo() == null) || (usuario.getSexo().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:sexo", new FacesMessage(ERRO_SEXO_NAO_INFORMADO, ERRO_SEXO_NAO_INFORMADO));
			gravar = false;
		}
		if((usuario.getCargo() == null) || (usuario.getCargo().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:cargo", new FacesMessage(ERRO_CARGO_NAO_INFORMADO, ERRO_CARGO_NAO_INFORMADO));
			gravar = false;
		}
		
		if((usuario.getTel() == null || (usuario.getTel().isEmpty()))){
			FacesContext.getCurrentInstance().addMessage("form:telefone", new FacesMessage(ERRO_TEL_NAO_INFORMADO, ERRO_TEL_NAO_INFORMADO));
			gravar = false;
		}
		if((usuario.getDataNascimento() == null)){
			FacesContext.getCurrentInstance().addMessage("form:dataNascimento", new FacesMessage(ERRO_DATA_NAO_INFORMADO, ERRO_DATA_NAO_INFORMADO));
			gravar = false;
		}
		if((usuario.getLogin() == null) || (usuario.getLogin().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:login", new FacesMessage(ERRO_LOGIN_NAO_INFORMADO, ERRO_LOGIN_NAO_INFORMADO));
			gravar = false;
		}
		if((usuario.getSenha() == null) || (usuario.getSenha().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:senha", new FacesMessage(ERRO_SENHA_NAO_INFORMADO, ERRO_SENHA_NAO_INFORMADO));
			gravar = false;
		}
		if((usuario.getSenha2() == null) || (usuario.getSenha2().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:senha2", new FacesMessage(ERRO_SENHA_NAO_INFORMADO, ERRO_SENHA_NAO_INFORMADO));
			gravar = false;
		}
		if(!(usuario.getSenha().equals(usuario.getSenha2()))){
			FacesContext.getCurrentInstance().addMessage("form:senha2", new FacesMessage(ERRO_SENHA_NAO_CONFERE, ERRO_SENHA_NAO_CONFERE));
			gravar = false;
		}
		if((usuario.getSenha().length() < 6) || (usuario.getSenha2().length() < 6)){
			FacesContext.getCurrentInstance().addMessage("form:senha2", new FacesMessage(ERRO_SENHA_CURTA, ERRO_SENHA_CURTA));
			gravar = false;
		}
		
		if(gravar)
			gravarUsuario();					
	
	}
	
	
	
	

	private void gravarUsuario() {
		ModeloUsuario modelo = new ModeloUsuario();
		ControleUsuario controle = new ControleUsuario();

		controle.setCadastroUsuario(this);
		controle.setModeloUsuario(modelo);

		controle.gravar();
	}

	@Override
	public void notificarUsuarioNaoEncontrado() {
		// TODO Auto-generated method stub
	}

	@Override
	public void notificarUsuarioGravado(Usuario usuario) {
		gravado = true;
	}

	public boolean getGravado() {
		return gravado;
	}

	@Override
	public void notificarErroGravacao() {
		// TODO Auto-generated method stub

	}

}
