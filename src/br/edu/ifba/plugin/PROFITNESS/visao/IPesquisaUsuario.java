package br.edu.ifba.plugin.PROFITNESS.visao;

import java.util.List;

import br.edu.ifba.plugin.PROFITNESS.modelo.bd.estatico.Usuario;

public interface IPesquisaUsuario {

	public String getId();
	
	public String getRg();

	public String getCpf();

	public String getNome();
	
	public String getCargo();

	///////////////////////

	public void atualizarUsuariosEncontrados(List<Usuario> usuarios);

	public void notificarUsuariosNaoEncontrados();
	
	public void notificarUsuarioRemovido();
	
	public void notificarErroRemocao();

}
