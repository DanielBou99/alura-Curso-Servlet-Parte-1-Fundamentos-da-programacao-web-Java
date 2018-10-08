package br.aluraservlet1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/novaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Cadastrando nova empresa!!");

		String nomeEmpersa = req.getParameter("nome");
		
		String paramDataEmpersa = req.getParameter("data");
		Date dataAbertura = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(paramDataEmpersa);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpersa);
		empresa.setDataAbertura(dataAbertura);

		Banco banco = new Banco();
		banco.adicionaEmpresa(empresa);

		// chamar o JSP
//		RequestDispatcher rd = req.getRequestDispatcher("/listaEmpresas");
//		req.setAttribute("nome_empresa", empresa.getNome());
//		rd.forward(req, resp);
		
		req.setAttribute("nome_empresa", empresa.getNome());
		resp.sendRedirect("listaEmpresas");

	}

}
