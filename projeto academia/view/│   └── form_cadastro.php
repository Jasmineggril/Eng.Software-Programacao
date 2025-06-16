<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Academia - Cadastrar Membro</title>
    <style>
        /* Estilos consistentes com a outra tela */
        body { font-family: sans-serif; background-color: #f9f9f9; }
        .container { width: 50%; margin: auto; background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 50px; }
        h1 { color: #333; text-align: center; }
        form { display: flex; flex-direction: column; }
        label { margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="date"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            padding: 10px 15px;
            border: none;
            background-color: #28a745;
            color: white;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover { background-color: #218838; }
        a { display: block; text-align: center; margin-top: 20px; color: #007BFF; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Cadastrar Novo Membro</h1>
        <form action="index.php?acao=cadastrar" method="post">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>

            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" required>

            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name="telefone">

            <button type="submit">Salvar</button>
        </form>
        <a href="index.php">Voltar para a Lista</a>
    </div>
</body>
</html>
