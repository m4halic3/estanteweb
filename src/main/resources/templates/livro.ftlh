<#include "cabecalho.ftlh">

<h2>${livro.titulo}</h2>

<p><strong>Autor:</strong> ${livro.autor}</p>
<p><strong>Ano:</strong> ${livro.ano?c}</p>
<p><strong>Preço:</strong> R$ ${livro.preco?string("0.00")}</p>

<#-- missão 4.4 (desafio): só aparece se houver outros títulos do autor -->
<#if outros?? && outros?has_content>
    <h3>Outros títulos de ${livro.autor}</h3>
    <ul>
        <#list outros as outro>
            <li><a href="/livros/${outro.id?c}">${outro.titulo}</a></li>
        </#list>
    </ul>
</#if>

<p><a href="/livros/html">Voltar para o acervo</a></p>

<p><a href="/livros/${livro.id?c}/editar">Corrigir</a></p>
<p><a href="/livros/${livro.id?c}/excluir">Excluir</a></p>
