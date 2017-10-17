<?php

/**
 * Recebe a URL com os parâmetros de usuário e senha preenchidos
 * Esses valores poderiam também ser enviados via POST através de um formulário
 */
$url = "http://localhost:8080/JSONExemplo/Produtos";

/**
 * Recupera o valor retornado pelo servidor Java
 */
$json = file_get_contents($url);

/**
 * Realiza a decodificação, transformando os dados em um array associativo
 */
$produtos = json_decode($json, TRUE);

/**
 * Verifica se o valor retornado não falhou
 */
if ($produtos["produtos"] == "vazio") {
    /**
     * Retorna uma mensagem de falha
     */
    echo 'Não há produtos para exibição';
} else {
    /**
     * Mostra a saída no navegador do cliente (solicitante da requisição)
     */
    foreach ($produtos as $listaProdutos) {
        foreach ($listaProdutos as $produto) {
            echo "Id: " . $produto["pId"] . "<br>";
            echo "Nome: " . $produto["pNome"] . "<br>";
            echo "Descrição: " . $produto["pDescricao"] . "<br>";
            echo "Valor: " . number_format($produto["pValor"], 2, ',', '.');

            echo "<br><br>";
        }
    }
}
    
