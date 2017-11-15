<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>

    <h1>Movies </h1>
    <h2> Movie id : ${movieid}</h2>

    <a> "b-1" -> </a> <a href="/v1/movie"> show movie list </a><br>
    <a> "b-2" -> </a> <a href="/v1/movie/random"> show rundom movies </a><br>
    <br>
    <a href="/v1/movie/${movieid}"> show movie with id ${movieid}</a><br>
</head>
<body>

</body>
</html>