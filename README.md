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

Nessa aula aprendemos que:  
-Apache Tomcat ou apenas Tomcat é um servidor web em Java
-Tomcat entende o protocolo HTTP e roda por padrão no http://localhost:8080
-O projeto Java faz parte da URL, no nosso caso: http://localhost:8080/gerenciador
-Uma aplicação web Java pode ter páginas HTML
-Uma servlet é um objeto Java que podemos chamar a partir de uma requisição HTTP
-Para mapear a URL para uma servlet usamos a anotação @WebServlet
-Uma servlet deve estender a classe HttpServlet e sobrescrever um determinado método (por exemplo service)
Na próxima aula veremos mais detalhes sobre as servlets!

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


[Voltar ao Índice](#indice)

---

## <a name="parte3">Definindo o nosso modelo</a>


[Voltar ao Índice](#indice)

---

## <a name="parte4">Páginas dinâmicas com JSP</a>


[Voltar ao Índice](#indice)

---

## <a name="parte5">JSTL e Expression Language</a>


[Voltar ao Índice](#indice)

---

## <a name="parte6">Redirecionando o fluxo</a>


[Voltar ao Índice](#indice)

---

## <a name="parte7">Completando o CRUD</a>


[Voltar ao Índice](#indice)

---

## <a name="parte8">Deploy da aplicação</a>


[Voltar ao Índice](#indice)

---
