<#include "cabecalho.ftlh">

<h1>Lista de Sugestões</h1>

    <#if sugestoes?size == 0>
        <p>Nenhuma sugestão no momento.</p>
    <#else>
        <ul>
            <#list sugestoes as sugestao>
                <li>${sugestao.nomeCliente} sugeriu: ${sugestao.livroSugerido}</li>
            </#list>
        </ul>
    </#if>