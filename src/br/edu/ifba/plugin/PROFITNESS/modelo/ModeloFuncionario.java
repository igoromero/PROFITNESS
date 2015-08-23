package br.edu.ifba.plugin.PROFITNESS.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.plugin.PROFITNESS.modelo.bd.Funcionario;
import br.edu.ifba.plugin.PROFITNESS.modelo.bd.FuncionarioDAO;
import br.edu.ifba.plugin.PROFITNESS.visao.ICadastroFuncionario;
import br.edu.ifba.plugin.PROFITNESS.visao.IPesquisaFuncionario;

/**
 * Classe de Modelo especializada em manipular informacoes relacionadas aa
 * validacao de acesso do usuario.
 * 
 * Os modelos sao ativos, i.e. acionam automaticamente acoes na interface.
 * 
 * @author PLUGIN
 */
public class ModeloFuncionario {
	
	private IPesquisaFuncionario pesquisaFuncionario = null;
	private ICadastroFuncionario cadastroFuncionario = null;

	
	public void setPesquisaFuncionario(IPesquisaFuncionario pesquisa) {
		this.pesquisaFuncionario = pesquisa;
	}

	public void setCadastroFuncionario(ICadastroFuncionario cadastro) {
		this.cadastroFuncionario = cadastro;
	}


	public void pesquisar() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		String criterio = pesquisaFuncionario.getCpf();
		if (!criterio.equals("")) {
			System.out.print(" "+ criterio);
			funcionarios = FuncionarioDAO.getFuncionarioPorCPF(criterio);
		} else {
			criterio = pesquisaFuncionario.getRg();
			if (!criterio.equals("")) {
				funcionarios = FuncionarioDAO.getFuncionarioPorRG(criterio);
			} else {
				criterio = pesquisaFuncionario.getNome();
				if (!criterio.equals("")) {
					funcionarios = FuncionarioDAO.getFuncionarioPorNome(criterio);
				} else{
					criterio = pesquisaFuncionario.getCargo();
					if(!criterio.equals("")){
						funcionarios = FuncionarioDAO.getFuncionarioPorCargo(criterio);
					}
				}
			}
		}

		pesquisaFuncionario.atualizarFuncionariosEncontrados(funcionarios);

		if (funcionarios.isEmpty()) {
			pesquisaFuncionario.notificarFuncionariosNaoEncontrados();
		}
	}

	public void pesquisarParaCadastro() {
		Funcionario funcionario = FuncionarioDAO.getFuncionario(cadastroFuncionario.getId());

		if (funcionario == null) {
			cadastroFuncionario.notificarFuncionarioNaoEncontrado();
		} else {
			cadastroFuncionario.atualizarFuncionarioEncontrado(funcionario);
		}
	}

	public void remover() {
		FuncionarioDAO.remover(Integer.parseInt(pesquisaFuncionario.getId()));

		pesquisar();
		
		pesquisaFuncionario.notificarFuncionarioRemovido();
	}

	public void adicionar() {
		Funcionario funcionario = cadastroFuncionario.getFuncionario();
		funcionario.setId(-1);
		if (FuncionarioDAO.gravar(funcionario) > 0) {
			cadastroFuncionario.notificarErroGravacao();
		} else {
			cadastroFuncionario.notificarFuncionarioGravado(funcionario);
		}
	}

	
	
	public void atualizar() {
		Funcionario funcionario = cadastroFuncionario.getFuncionario();

		if (FuncionarioDAO.gravar(funcionario) > 0) {
			cadastroFuncionario.notificarErroGravacao();
		} else {
			cadastroFuncionario.notificarFuncionarioGravado(funcionario);
		}
	}

}
