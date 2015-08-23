package br.edu.ifba.plugin.PROFITNESS.visao.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.PROFITNESS.controle.ControleFuncionario;
import br.edu.ifba.plugin.PROFITNESS.modelo.ModeloFuncionario;
import br.edu.ifba.plugin.PROFITNESS.modelo.bd.Funcionario;
import br.edu.ifba.plugin.PROFITNESS.visao.IPesquisaFuncionario;

@ManagedBean(name = "pfuncionario")
@ViewScoped
public class PesquisaFuncionario implements IPesquisaFuncionario {

	private String erro;
	private String sucesso;
	
	private String id = "";
	private String rg = "";
	private String cpf = "";
	private String cargo = "";
	private String nome = "";
	
	
	
	
	private List<Funcionario> funcionariosEncontrados = new ArrayList<Funcionario>();

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	
	
	
	public void pesquisar() {
		erro = null;
			

		ModeloFuncionario modelo = new ModeloFuncionario();
		ControleFuncionario controle = new ControleFuncionario();

		controle.setModeloFuncionario(modelo);
		controle.setPesquisaFuncionario(this);

		controle.pesquisar();
	}

	@Override
	public void atualizarFuncionariosEncontrados(List<Funcionario> funcionarios) {
		this.funcionariosEncontrados = funcionarios;
	}

	public List<Funcionario> getFuncionarios() {
		return this.funcionariosEncontrados;
	}

	private void exibirCadastro(String id) {
		ExternalContext context = FacesContext.
				getCurrentInstance()
				.getExternalContext();
		context.getSessionMap().put("idFuncionario", id);
		try {
			context.redirect("incluir_funcionario.ifba");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ver(String id) {
		exibirCadastro(id);
	}

	public void adicionar() {
		exibirCadastro("");
	}

	public void remover(String id) {
		sucesso = null;
		erro = null;
		
		this.id = id;

		ModeloFuncionario modelo = new ModeloFuncionario();
		ControleFuncionario controle = new ControleFuncionario();

		controle.setModeloFuncionario(modelo);
		controle.setPesquisaFuncionario(this);

		controle.remover();
	}

	public String getErro() {
		return erro;
	}

	public String getSucesso() {
		return sucesso;
	}
	
	@Override
	public void notificarFuncionariosNaoEncontrados() {
		erro = "Nenhum funcionario foi encontrado";
	}

	@Override
	public void notificarFuncionarioRemovido() {
		sucesso = "Funcionario removido com sucesso";
	}

	@Override
	public void notificarErroRemocao() {
		erro = "Nao foi possivel remover o funcionario";
	}

}
