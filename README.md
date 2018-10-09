# Curso Servlet Parte 1: Fundamentos da programação web Java

https://cursos.alura.com.br/course/servlets-fundamentos-programacao-web-java

---

## <a name="indice">Índice</a>

- [Fundamentos da Web e a API de Servlets](#parte1)   
- [Trabalhando com POST e GET](#parte2)   
- [Definindo o nosso modelo](#parte3)   
- [Páginas dinâmicas com JSP](#parte4)   
- [JSTL e Expression Language](#parte5)   
- [Redirecionando o fluxo](#parte6)   
- [Completando o CRUD](#parte7)   
- [Deploy da aplicação](#parte8)   

---

## <a name="parte1">Fundamentos da Web e a API de Servlets</a>

05 Apache HTTP ou Apache Tomcat?

Existem literalmente milhares de servidores web, cada linguagem e plataforma possui os seus. No mundo Java o Apache Tomcat talvez é um dos mais famosos mas existem outros. 

O Tomcat faz parte da Apache Foundation que é uma organização que desenvolve vários projetos Open Source. Agora a Apache Foundation possui um outro servidor ainda mais famoso, o Apache HTTP (também é chamado apenas Apache). Ambos, Apache HTTP e Apache Tomcat são servidores web. Qual é a diferença então?

O Tomcat é puramente Java enquanto Apache HTTP é escrito em C. Além disso, o Apache HTTP é uma servidor estático (por padrão pelo menos) e precisa de alguma integração com outra linguagem para se gerar HTML dinamicamente. O Tomcat já é dinâmico de cara, usando Java e JSP, claro!


13 O que aprendemos?  

- Apache Tomcat ou apenas Tomcat é um servidor web em Java  
- Tomcat entende o protocolo HTTP e roda por padrão no http://localhost:8080  
- O projeto Java faz parte da URL, no nosso caso: http://localhost:8080/gerenciador  
- Uma aplicação web Java pode ter páginas HTML  
- Uma servlet é um objeto Java que podemos chamar a partir de uma requisição HTTP  
- Para mapear a URL para uma servlet usamos a anotação @WebServlet  
- Uma servlet deve estender a classe HttpServlet e sobrescrever um determinado método (por exemplo service)  

- /gerenciador/src/br/aluraservlet1/OiMundoServlet.java

```java
package br.aluraservlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//oi
@WebServlet(urlPatterns=("/oi"))
public class OiMundoServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Ola mundo Servlet");
		out.println("</body>");
		out.println("</html>");
		
		System.out.println("O servlet foi chamado!!!");
				
	}
	
}

```

[Voltar ao Índice](#indice)

---

## <a name="parte2">Trabalhando com POST e GET</a>


#### 02 Enviando parâmetros
```java
package br.aluraservlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/novaempresa")
public class NovaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Cadastrando nova empresa!!");
		
		String nomeEmpersa = request.getParameter("nome");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Empresa Cadastrada "+nomeEmpersa+" com sucesso!");
		out.println("</body>");
		out.println("</html>");
		
	}

}

```

#### 03 Parâmetros da requisição  

- /gerenciador/WebContent/formNovaEmpresa.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/gerenciador/novaEmpresa" method="POST">
	
		Nome: <input type="text" name="nome" />
		<input type="submit" />
	
	</form>
</body>
</html>
```

#### 07 Apenas POST

```java
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
	/*protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Cadastrando nova empresa!!");
		
		String nomeEmpersa = request.getParameter("nome");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Empresa Cadastrada "+nomeEmpersa+" com sucesso!");
		out.println("</body>");
		out.println("</html>");
	}*/
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
System.out.println("Cadastrando nova empresa!!");
		
		String nomeEmpersa = req.getParameter("nome");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Empresa Cadastrada "+nomeEmpersa+" com sucesso!");
		out.println("</body>");
		out.println("</html>");
	
	}

}

```


[Voltar ao Índice](#indice)

---

## <a name="parte3">Definindo o nosso modelo</a>

#### 02 Definido modelo

```java
package br.aluraservlet1;

public class Empresa {

	private Integer id;
	private String nome;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}

```

```java
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

```

```java
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
		
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Empresa Cadastrada "+nomeEmpersa+" com sucesso!");
		out.println("</body>");
		out.println("</html>");
	
	}

}

```


```java
package br.aluraservlet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListaEmpresas")
public class ListaEmpresasServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Banco banco = new Banco();
		List<Empresa> lista = banco.getListaEmpresas();
		
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<ul>");
		for (Empresa empresa : lista) {
			out.println("<li>"+empresa.getNome()+"</li>");
		}
		out.println("</ul>");
		out.println("</body></html>");
		
		
	}

}

```



[Voltar ao Índice](#indice)

## <a name="parte4">Páginas dinâmicas com JSP</a>

```jsp
<%
	String nomeEmpresa = "ALURA Teste";
	System.out.println(nomeEmpresa);
%>
<html>
<head>
<title>Empresa Criada</title>
</head>
	<body>
		Empresa Cadastrada <%=nomeEmpresa %> com sucesso!
	</body>
</html>
```






[Voltar ao Índice](#indice)

---

## <a name="parte5">JSTL e Expression Language</a>

- /gerenciador/WebContent/novaEmpresaCriada.jsp

```jsp
<html>
<head>
<title>Empresa Criada</title>
</head>
<body>
	Empresa Cadastrada ${nome_empresa } com sucesso, ok!!
</body>
</html>
```
- /gerenciador/WebContent/formNovaEmpresa.jsp

```jsp
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="br.aluraservlet1.Empresa"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página Lista de Empresas JSP</title>
</head>
<body>
	<h3>Lista de Empresas (JSP)</h3>
	
	<ul>
		<c:forEach items="${empresas}" var="empresa">
			<li>${empresa.nome} - <fmt:formatDate pattern="dd/MM/yyyy" value="${empresa.dataAbertura }"/></li>	
		</c:forEach>
	</ul>
</body>
</html>
```

```java
package br.aluraservlet1;

import java.util.Date;

public class Empresa {

	private Integer id;
	private String nome;
	//private Date dataAbertura = new Date();
	private Date dataAbertura;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	

}

```

```java
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
		RequestDispatcher rd = req.getRequestDispatcher("/novaEmpresaCriada.jsp");
		req.setAttribute("nome_empresa", empresa.getNome());
		rd.forward(req, resp);

	}

}

```


- /gerenciador/WebContent/novaEmpresaCriada.jsp

```jsp
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/novaEmpresa" var="linkServletNovaEmpersa" />	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FORM NOVA EMPRESA - JSP</title>
</head>
<body>
	<form action="${linkServletNovaEmpersa}" method="POST">
	
		Nome: <input type="text" name="nome" />
		Data Abertura <input type="text" name="data"/>
		<input type="submit" />
	
	</form>
</body>
</html>
```





[Voltar ao Índice](#indice)

---

## <a name="parte6">Redirecionando o fluxo</a>

```jsp
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

```


[Voltar ao Índice](#indice)

---

## <a name="parte7">Completando o CRUD</a>

#### Removendo

```jsp
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="br.aluraservlet1.Empresa"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página Lista de Empresas JSP</title>
</head>
<body>
	<h3>Lista de Empresas (JSP)</h3>

<br>
	<c:if test="${not empty nome_empresa }">
		Empresa Cadastrada ${nome_empresa } com sucesso, ok!!
	</c:if>
<br>
	
	<ul>
		<c:forEach items="${empresas}" var="empresa">
		
			<li>${empresa.nome} - 
			<fmt:formatDate pattern="dd/MM/yyyy" value="${empresa.dataAbertura }"/> - 
			<a href="/gerenciador/removeEmpresa?id=${empresa.id }">Remove</a> 
			</li>	
		</c:forEach>
	</ul>

</body>
</html>
```

```java
package br.aluraservlet1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removeEmpresa")
public class RemoveEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paramId = request.getParameter("id");
		
		Integer id = Integer.valueOf(paramId);
		System.out.println(id);
		
		Banco banco = new Banco();
		banco.removeEmrpesa(id);
		
		response.sendRedirect("listaEmpresas");
		
	}

}

```

```java
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

}

```

#### Alterando

```java
package br.aluraservlet1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@WebServlet("/alteraEmpresa")
public class AlteraEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ALTERANDO nova empresa");
		
		String nomeEmpresa = request.getParameter("nome");
		String paramDataEmpresa = request.getParameter("data");
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		Date dataAbertura = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(paramDataEmpresa);
		} catch (ParseException | java.text.ParseException e) {
			throw new ServletException(e);
		}
		
		System.out.println(id);
		
		Banco banco = new Banco();
		Empresa empresa = banco.buscaEmpresaId(id);
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura); // tudo em "memoria"
		
		response.sendRedirect("listaEmpresas");
		
	}

}

```

```jsp
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<c:url value="/alteraEmpresa" var="linkServletNovaEmpersa" />	
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FORM NOVA EMPRESA - JSP</title>
</head>
<body>
	<form action="${linkServletNovaEmpersa}" method="POST">
	
		Nome: <input type="text" name="nome" value="${empresa.nome}" />
		Data Abertura <input type="text" name="data" 
		 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${empresa.dataAbertura}"/>"/>
		<input type="hidden" name="id" value="${empresa.id }"  >
		<input type="submit" />
	
	</form>
</body>
</html>
```

```java
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

```

```jsp
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="br.aluraservlet1.Empresa"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página Lista de Empresas JSP</title>
</head>
<body>
	<h3>Lista de Empresas (JSP)</h3>

<br>
	<c:if test="${not empty nome_empresa }">
		Empresa Cadastrada ${nome_empresa } com sucesso, ok!!
	</c:if>
<br>
	
	<ul>
		<c:forEach items="${empresas}" var="empresa">
		
			<li>${empresa.nome} - 
			<fmt:formatDate pattern="dd/MM/yyyy" value="${empresa.dataAbertura }"/> - 
			<a href="/gerenciador/mostraEmpresa?id=${empresa.id }">Editar</a> -  
			<a href="/gerenciador/removeEmpresa?id=${empresa.id }">Remove</a> 
			</li>	
		</c:forEach>
	</ul>

</body>
</html>
```



[Voltar ao Índice](#indice)

---

## <a name="parte8">Deploy da aplicação</a>


[Voltar ao Índice](#indice)

---
