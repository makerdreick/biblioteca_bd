
<%@page import="tec.emergentes.modelo.Libro"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page  import="java.util.List"%>
<%
    List<Libro> lista = (List<Libro>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>biblioteca</title>
    </head>
    <body>
        <h1>Lista de libros</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table  border="1" >
            <tr>
                <th>ID</th>
                <th>ISBN</th>
                <th>TITULO</th>
                <th>CATEGORIA</th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.isbn}</td>
                    <td>${item.titulo}</td>
                    <td>${item.categoria}</td>
                    <td><a href="MainController?op=editar&id=${item.id}" onclick="return (confirm('Quiere Editar📚📒'))">Editar</a> </td>
                </tr>
            </c:forEach>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.isbn}</td>
                    <td>${item.titulo}</td>
                    <td>${item.categoria}</td>
                    <td><a href="MainController?op=eliminar&id=${item.id}" onclick="return (confirm('Esta Seguro ???😎'))">Eliminar</a> </td>
                </tr>
            </c:forEach>
        </table>
        <a href="index.jsp" >Actualizar la Pagina</a>
    </body>
</html>
