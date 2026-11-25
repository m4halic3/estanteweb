<h4>Estante Web - Sebo de qualidade</h4>
<p><a href="/">Voltar para a página inicial</a></p>

<#if usuarioLogado??>
    <form method="POST" action="/logout">
        <span>Olá, ${usuarioLogado.nome}</span>
        <button type="submit">Sair</button>
    </form>
<#else>
    <p><a href="/login">Entrar</a></p>
</#if>