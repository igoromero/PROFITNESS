package br.edu.ifba.plugin.PROFITNESS.visao;

import br.edu.ifba.plugin.PROFITNESS.modelo.bd.estatico.Usuario;

public interface ICadastroUsuario {

	public int getId();

	public Usuario getUsuario();

	// /////////////////////

	public void atualizarUsuarioEncontrado(Usuario usuario);

	public void notificarUsuarioNaoEncontrado();

	public void notificarUsuarioGravado(Usuario usuario);

	public void notificarErroGravacao();

}
