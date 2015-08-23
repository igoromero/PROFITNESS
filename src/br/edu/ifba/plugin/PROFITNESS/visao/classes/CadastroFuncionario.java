package br.edu.ifba.plugin.PROFITNESS.visao.classes;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.PROFITNESS.controle.ControleFuncionario;
import br.edu.ifba.plugin.PROFITNESS.modelo.ModeloFuncionario;
import br.edu.ifba.plugin.PROFITNESS.modelo.bd.Funcionario;
import br.edu.ifba.plugin.PROFITNESS.visao.ICadastroFuncionario;

@ManagedBean(name = "cfuncionario")
@ViewScoped
public class CadastroFuncionario implements ICadastroFuncionario {
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
	private Funcionario funcionario = new Funcionario();

	public CadastroFuncionario() {
		ExternalContext contexto = FacesContext.getCurrentInstance()
				.getExternalContext();
		id = (String) contexto.getSessionMap().get("idFuncionario");

		if ((id != null) && (!id.isEmpty())) {
			recuperarFuncionario();
		}
	}

	private void recuperarFuncionario() {
		ModeloFuncionario modelo = new ModeloFuncionario();
		ControleFuncionario controle = new ControleFuncionario();

		controle.setCadastroFuncionario(this);
		controle.setModeloFuncionario(modelo);

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
	public Funcionario getFuncionario() {
		return funcionario;
	}

	@Override
	public void atualizarFuncionarioEncontrado(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void gravar() {
		gravado = false;
		gravar = true;	
		
		if ((funcionario.getCpf() == null) || (funcionario.getCpf().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:cpf",new FacesMessage(ERRO_CPF_NAO_INFORMADO,ERRO_CPF_NAO_INFORMADO));
			gravar = false;
		}
		if((funcionario.getRg() == null) || (funcionario.getRg().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:rg", new FacesMessage(ERRO_RG_NAO_INFORMADO, ERRO_RG_NAO_INFORMADO));
			gravar = false;
		}
		if((funcionario.getNome() == null) || (funcionario.getNome().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:nome", new FacesMessage(ERRO_Nome_NAO_INFORMADO, ERRO_Nome_NAO_INFORMADO));
			gravar = false;
		}
		if((funcionario.getSexo() == null) || (funcionario.getSexo().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:sexo", new FacesMessage(ERRO_SEXO_NAO_INFORMADO, ERRO_SEXO_NAO_INFORMADO));
			gravar = false;
		}
		if((funcionario.getCargo() == null) || (funcionario.getCargo().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:cargo", new FacesMessage(ERRO_CARGO_NAO_INFORMADO, ERRO_CARGO_NAO_INFORMADO));
			gravar = false;
		}
		
		if((funcionario.getTel() == null || (funcionario.getTel().isEmpty()))){
			FacesContext.getCurrentInstance().addMessage("form:telefone", new FacesMessage(ERRO_TEL_NAO_INFORMADO, ERRO_TEL_NAO_INFORMADO));
			gravar = false;
		}
		if((funcionario.getDataNascimento() == null)){
			FacesContext.getCurrentInstance().addMessage("form:dataNascimento", new FacesMessage(ERRO_DATA_NAO_INFORMADO, ERRO_DATA_NAO_INFORMADO));
			gravar = false;
		}
		if((funcionario.getLogin() == null) || (funcionario.getLogin().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:login", new FacesMessage(ERRO_LOGIN_NAO_INFORMADO, ERRO_LOGIN_NAO_INFORMADO));
			gravar = false;
		}
		if((funcionario.getSenha() == null) || (funcionario.getSenha().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:senha", new FacesMessage(ERRO_SENHA_NAO_INFORMADO, ERRO_SENHA_NAO_INFORMADO));
			gravar = false;
		}
		if((funcionario.getSenha2() == null) || (funcionario.getSenha2().isEmpty())){
			FacesContext.getCurrentInstance().addMessage("form:senha2", new FacesMessage(ERRO_SENHA_NAO_INFORMADO, ERRO_SENHA_NAO_INFORMADO));
			gravar = false;
		}
		if(!(funcionario.getSenha().equals(funcionario.getSenha2()))){
			FacesContext.getCurrentInstance().addMessage("form:senha2", new FacesMessage(ERRO_SENHA_NAO_CONFERE, ERRO_SENHA_NAO_CONFERE));
			gravar = false;
		}
		if((funcionario.getSenha().length() < 6) || (funcionario.getSenha2().length() < 6)){
			FacesContext.getCurrentInstance().addMessage("form:senha2", new FacesMessage(ERRO_SENHA_CURTA, ERRO_SENHA_CURTA));
			gravar = false;
		}
		
		if(gravar)
			gravarFuncionario();				
	
	}
	
	
	
	

	private void gravarFuncionario() {
		ModeloFuncionario modelo = new ModeloFuncionario();
		ControleFuncionario controle = new ControleFuncionario();

		controle.setCadastroFuncionario(this);
		controle.setModeloFuncionario(modelo);

		controle.gravar();
	}

	@Override
	public void notificarFuncionarioNaoEncontrado() {
		// TODO Auto-generated method stub
	}

	@Override
	public void notificarFuncionarioGravado(Funcionario funcionario) {
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
