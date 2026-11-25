<#include "cabecalho.ftlh">

<h1>Entrar</h1>

<#if erro??>
    <p style="color: red;">${erro}</p>
</#if>

<form method="POST" action="/login">
    <p>
        <label for="email">E-mail</label><br>
        <input type="email" id="email" name="email" value="${email!''}" required>
    </p>
    <p>
        <label for="senha">Senha</label><br>
        <input type="password" id="senha" name="senha" required>
    </p>
    <button type="submit">Entrar</button>
</form>