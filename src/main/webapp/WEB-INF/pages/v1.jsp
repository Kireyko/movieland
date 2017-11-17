<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>

    <h1>Movies </h1>
    <h2> Movie id : ${movieid}</h2>

    <a> "b-1" -> </a> <a href="/v1/movie"> get all movies </a><br>
    <a> "b-2" -> </a> <a href="/v1/movie/random"> get 3 rundom movies </a><br>
    <br>
    <a> "b-3" -> </a> <a href="/v1/genre"> get all genres </a><br>
    <br>
    <a> "b-4" -> </a> <a href="/v1/movie/genre/${genreId}"> get movie by genre </a><br>
    <br>
    <a> "b-6" -> (for now - without details) -> </a> <a href="/v1/movie/${movieid}"> show movie by id ${movieid}</a><br>
</head>
<body>

</body>
</html>