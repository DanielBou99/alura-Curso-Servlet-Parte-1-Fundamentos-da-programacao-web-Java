package br.aluraservlet1;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	
	private static List<Empresa> listaEmpresas = new ArrayList<>();
	
	static {
		Empresa empresa = new Empresa();
		Empresa empresa2 = new Empresa();
		empresa.setNome("Alura Static");
		empresa2.setNome("Caelum Static");
		listaEmpresas.add(empresa);
		listaEmpresas.add(empresa2);
	}

	public void adicionaEmpresa(Empresa empresa) {
		Banco.listaEmpresas.add(empresa);
	}

	public List<Empresa> getListaEmpresas() {
		return Banco.listaEmpresas;
	}

}
