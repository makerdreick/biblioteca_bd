<%@page import="tec.emergentes.modelo.Libro"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
    Libro lib = (Libro) request.getAttribute("lib");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>edita Libro</title>
    </head>
    <body>
        <h1>Nuevo Libro</h1>
        <form name="Post" action="MainController" method="POST">
             <table>
            <input type="hidden" name="id" value="${lib.id}">
            <tr>
                <td>ISBN</td>
                <td><input type="text" name="isbn" value="${lib.isbn}"></td>
            </tr>
            <tr>
                <td>TITULO</td>
                <td> <input  type="text" name="titulo" value="${lib.titulo}"></td>
            </tr>
            <tr>
                <td>CATEGORIA</td>
                <td><input type="text" name="categoria" value="${lib.categoria}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Enviar"></td>
            </tr>
        </table>
            <a href="index.jsp">Volver</a>
        </form>
</body>
</html>
