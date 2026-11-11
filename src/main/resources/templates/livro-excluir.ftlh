<#include "cabecalho.ftlh">

<p>Deseja excluir o livro <strong>${livro.titulo}</strong>, de ${livro.autor}?</p>

<form method="post" action="/livros/${livro.id?c}/excluir">
    <button type="submit">Excluir</button>
</form>

<p><a href="/livros/${livro.id?c}">Cancelar</a></p>
