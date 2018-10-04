package br.aluraservlet1;

import java.io.IOException;
import java.io.PrintWriter;

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
		Empresa empresa = new Empresa();
		empresa.setNome(nomeEmpersa);
		
		Banco banco = new Banco();
		banco.adicionaEmpresa(empresa);
		
		/*		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Empresa Cadastrada "+nomeEmpersa+" com sucesso!");
		out.println("</body>");
		out.println("</html>");*/
	
	}

}
