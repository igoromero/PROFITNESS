package br.edu.ifba.plugin.PROFITNESS.controle;

import br.edu.ifba.plugin.PROFITNESS.modelo.ModeloFuncionario;
import br.edu.ifba.plugin.PROFITNESS.visao.ICadastroFuncionario;
import br.edu.ifba.plugin.PROFITNESS.visao.IPesquisaFuncionario;

public class ControleFuncionario {

	private IPesquisaFuncionario pesquisaFuncionario;
	private ICadastroFuncionario cadastroFuncionario;
	private ModeloFuncionario modeloFuncionario;

	public void setPesquisaFuncionario(IPesquisaFuncionario pesquisa) {
		this.pesquisaFuncionario = pesquisa;
	}

	public void setCadastroFuncionario(ICadastroFuncionario cadastro) {
		this.cadastroFuncionario = cadastro;
	}

	public void setModeloFuncionario(ModeloFuncionario modelo) {
		this.modeloFuncionario = modelo;
	}

	public void pesquisar() {
		modeloFuncionario.setPesquisaFuncionario(pesquisaFuncionario);
		modeloFuncionario.pesquisar();
	}

	public void pesquisarParaCadastro() {
		modeloFuncionario.setCadastroFuncionario(cadastroFuncionario);
		modeloFuncionario.pesquisarParaCadastro();
	}

	public void remover() {
		modeloFuncionario.setPesquisaFuncionario(pesquisaFuncionario);
		modeloFuncionario.remover();
	}

	public void gravar() {
		modeloFuncionario.setCadastroFuncionario(cadastroFuncionario);
		
		if (cadastroFuncionario.getId() == -1) {
			modeloFuncionario.adicionar();
		} else {
			modeloFuncionario.atualizar();
		}
	}

}
