<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>

    <h1>Movies list </h1>
    <h2> Movie : ${movie}</h2>
    <h2> Movie id : ${movieid}</h2>

    <a href="/v1/movie/${movieid}"> show movie with id ${movieid}</a><br>
    <a href="/v1/movie/random"> show rundom movies </a><br>
    <a href="/v1/movie"> show all list </a><br>


</head>
<body>

</body>
</html>