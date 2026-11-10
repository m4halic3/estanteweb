<#include "cabecalho.ftlh">

<h2>Corrigir livro</h2>

<#if erro??>
    <p class="erro">${erro}</p>
</#if>

<form method="post" action="/livros/${livro.id?c}/editar">
    <label>Título: <input type="text" name="titulo" value="${(livro.titulo)!}"></label><br/>
    <label>Autor: <input type="text" name="autor" value="${(livro.autor)!}"></label><br/>
    <label>Ano: <input type="text" name="ano" value="${(livro.ano?c)!}"></label><br/>
    <label>Preço: <input type="text" name="preco" value="${(livro.preco?c)!}"></label><br/>
    <button type="submit">Salvar correção</button>
</form>

<p><a href="/livros/${livro.id?c}">Cancelar</a></p>
