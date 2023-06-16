<%--
  Created by IntelliJ IDEA.
  User: samoel.laureano
  Date: 13/06/2023
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cadastro de Consulta Médica</title>
  <style>
    /* Estilos CSS */
    .container {
      width: 400px;
      margin: 0 auto;
    }

    .form-group {
      margin-bottom: 10px;
    }

    .form-group label {
      display: block;
      font-weight: bold;
    }

    .form-group input {
      width: 100%;
      padding: 5px;
    }

    .form-group select {
      width: 100%;
      padding: 5px;
    }

    .btn {
      padding: 8px 16px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Cadastro de Consulta Médica</h2>
  <form id="consultaForm" action="cadastrar-consulta" method="POST">
    <div class="form-group">
      <label for="nome">Nome:</label>
      <input type="text" id="nome" name="nome" required>
    </div>
    <div class="form-group">
      <label for="cpf">CPF:</label>
      <input type="text" id="cpf" name="cpf" required>
    </div>
    <div class="form-group">
      <label for="idade">Idade:</label>
      <input type="number" id="idade" name="idade" required>
    </div>
    <div class="form-group">
      <label for="sexo">Sexo:</label>
      <select id="sexo" name="sexo" required>
        <option value="M">Masculino</option>
        <option value="F">Feminino</option>
      </select>
    </div>
    <div class="form-group">
      <button type="submit" class="btn">Cadastrar</button>
    </div>
  </form>
</div>

<script>
  // JavaScript para fazer a requisição à servlet
  document.getElementById("consultaForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores dos campos
    var nome = document.getElementById("nome").value;
    var cpf = document.getElementById("cpf").value;
    var idade = document.getElementById("idade").value;
    var sexo = document.getElementById("sexo").value;

    // Cria o objeto de dados a ser enviado
    var data = {
      nome: nome,
      cpf: cpf,
      idade: idade,
      sexo: sexo
    };

    // Faz a requisição AJAX para a servlet
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "cadastrar-consulta");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          // Requisição bem-sucedida, faça algo com a resposta
          console.log(xhr.responseText);
        } else {
          // Erro na requisição
          console.error(xhr.statusText);
        }
      }
    };
    xhr.send(JSON.stringify(data));
  });
</script>
</body>
</html>

