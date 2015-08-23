package br.edu.ifba.plugin.PROFITNESS.visao;

import java.util.List;

import br.edu.ifba.plugin.PROFITNESS.modelo.bd.Funcionario;

public interface IPesquisaFuncionario {

	public String getId();
	
	public String getRg();

	public String getCpf();

	public String getNome();
	
	public String getCargo();

	///////////////////////

	public void atualizarFuncionariosEncontrados(List<Funcionario> funcionarios);

	public void notificarFuncionariosNaoEncontrados();
	
	public void notificarFuncionarioRemovido();
	
	public void notificarErroRemocao();

}
