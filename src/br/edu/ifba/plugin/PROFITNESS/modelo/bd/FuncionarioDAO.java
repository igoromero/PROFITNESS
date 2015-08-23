package br.edu.ifba.plugin.PROFITNESS.modelo.bd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class FuncionarioDAO {

	private static Map<Integer, Funcionario> funcionarios = 
			new TreeMap<Integer, Funcionario>();
	
	static {
		
		Funcionario u1 = new Funcionario();
		u1.setId(1);
		u1.setCpf("1234");
		u1.setRg("1234");
		u1.setCargo("Gerente");
		u1.setNome("Igo Romero");
		u1.setLogin("igo");
		u1.setSenha("123");
		
		funcionarios.put(u1.getId(), u1);
		
	}
	
	
	
	public static List<Funcionario> getFuncionarioPorCPF(String cpf)
	{
		List<Funcionario> encontrados = new ArrayList<Funcionario>();
		
		for (Funcionario u : funcionarios.values()) {
			if (u.getCpf().equals(cpf)) {
				encontrados.add(u);
			}
		}
		
		return encontrados;
	}
	
	public static List<Funcionario> getFuncionarioPorCargo(String cargo)
	{
		List<Funcionario> encontrados = new ArrayList<Funcionario>();
		
		for (Funcionario u : funcionarios.values()) {
			if (u.getCargo().equals(cargo)) {
				encontrados.add(u);
			}
		}
		
		return encontrados;
	}

	public static List<Funcionario> getFuncionarioPorRG(String rg)
	{
		List<Funcionario> encontrados = new ArrayList<Funcionario>();
		
		for (Funcionario u : funcionarios.values()) {
			if (u.getRg().equals(rg)) {
				encontrados.add(u);
			}
		}
		
		return encontrados;
	}
	
	public static List<Funcionario> getFuncionarioPorNome(
			String nome)
	{
		List<Funcionario> encontrados = new ArrayList<Funcionario>();
		
		for (Funcionario u : funcionarios.values()) {
			if (u.getNome().toLowerCase().
					contains(nome.toLowerCase())) {
				encontrados.add(u);
			}
		}
		
		return encontrados;
	}
	
	
	
	public static Funcionario getFuncionario(int id) {
		return funcionarios.get(id);
	}
	
	public static void remover(int id) {
		funcionarios.remove(id);
	}
	
	public static int gravar(Funcionario funcionario) {
		if (funcionario.getId() != -1) {
			remover(funcionario.getId());
			funcionarios.put(funcionario.getId(), funcionario);
		} else {
			int ultimoId = 0;
			for (int id : funcionarios.keySet()) {
				ultimoId = id;
			}
			funcionario.setId(ultimoId + 1);
			funcionarios.put(ultimoId + 1, funcionario);
		}
		System.out.print(""+funcionario.toString());
		return 0;
	}
}





