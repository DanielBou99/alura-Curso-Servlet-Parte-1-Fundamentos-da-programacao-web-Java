package br.aluraservlet1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	
	private static List<Empresa> listaEmpresas = new ArrayList<>();
	private static Integer chaveSequencial = 1;
	
	static {
		Empresa empresa = new Empresa();
		Empresa empresa2 = new Empresa();
		empresa.setNome("Alura Static");
		empresa2.setNome("Caelum Static");
		listaEmpresas.add(empresa);
		listaEmpresas.add(empresa2);
		empresa.setId(chaveSequencial++);
		empresa2.setId(chaveSequencial++);
	}

	public void adicionaEmpresa(Empresa empresa) {
		empresa.setId(Banco.chaveSequencial++);
		Banco.listaEmpresas.add(empresa);
	}

	public List<Empresa> getListaEmpresas() {
		return Banco.listaEmpresas;
	}

	public void removeEmrpesa(Integer id) {
		
		Iterator<Empresa> it = listaEmpresas.iterator();
		
		while(it.hasNext()) {
			Empresa emp = it.next();
			if(emp.getId() == id) {
				it.remove();
			}
		}
		
		/*for (Empresa empresa : listaEmpresas) {
			if(empresa.getId() == id) {
				listaEmpresas.remove(empresa);
			}
		}*/
		
	}

	public Empresa buscaEmpresaId(Integer id) {
		for(Empresa empresa : listaEmpresas) {
			if(empresa.getId() == id) {
				return empresa;
			}
		}
		return null;
	}

}
