<#include "cabecalho.ftlh">

<#if erro??>
    <p class="erro">${erro}</p>
</#if>

<form method="post" action="/sugestoes">
    <label>Nome: <input type="text" name="nomeCliente" value="${nomeCliente!}"></label><br/>
    <label>Livro: <input type="text" name="livroSugerido" value="${livroSugerido!}"></label><br/>
    <button type="submit">Enviar</button>
</form>