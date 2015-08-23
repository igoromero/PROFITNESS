package br.edu.ifba.plugin.PROFITNESS.visao;

import br.edu.ifba.plugin.PROFITNESS.modelo.bd.Funcionario;

public interface ICadastroFuncionario {

	public int getId();

	public Funcionario getFuncionario();

	// /////////////////////

	public void atualizarFuncionarioEncontrado(Funcionario funcionario);

	public void notificarFuncionarioNaoEncontrado();

	public void notificarFuncionarioGravado(Funcionario funcionario);

	public void notificarErroGravacao();

}
