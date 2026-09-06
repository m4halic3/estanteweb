<#include "cabecalho.ftlh">

<#if erro??>
    <p class="erro">${erro}</p>
</#if>

<form method="post" action="/contato">
    <label>Nome: <input type="text" name="nome" value="${nome!}"></label><br/>
    <label>Mensagem: <input type="text" name="mensagem"></label><br/>
    <button type="submit">Enviar</button>
</form>