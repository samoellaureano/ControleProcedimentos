<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Controle de Procedimentos</title><meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="css/fonts.css">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/icon.css">
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <link rel="stylesheet" href="css/autorizacao.css">
  <script src="js/jquery-3.5.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/ajax.js"></script>
  <script src="js/jquery.mask.min.js" ></script>
  <script src="js/Autorizacao.js"></script>
  <script src="js/mascaraCnpj.js"></script>
</head>
<body>
<div class="container-xl">
  <div class="container-msg">
    <div id="msg" class="msg"></div>
  </div>
  <div class="table-responsive">
    <div class="table-wrapper">
      <div class="table-title">
        <div class="row">
          <div class="col-sm-6">
            <h2>Autorização de Procedimentos</h2>
          </div>
          <div class="col-sm-6">
            <a href="#addAutorizacaoModal" class="btn" data-toggle="modal"><i
                    class="material-icons">&#xE147;</i> <span>Add Nova Autorização</span></a>
          </div>
        </div>
      </div>
      <table id="tbAutorizacoes" class="table table-striped table-hover">
        <thead>
        <tr>
          <th>Procedimento</th>
          <th>Paciente</th>
          <th>Data Cadastro Autorização</th>
          <th>Autorizado</th>
          <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
    </div>
  </div>
</div>
<!-- Add Modal HTML -->
<div id="addAutorizacaoModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form onsubmit="javascript:cadAutorizacao(this)">
        <div class="modal-header">
          <h4 class="modal-title">Add Autorização</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="idPaciente">Paciente</label>
            <input type="search" id="idPaciente"  list="pacientes" class="form-control" placeholder="Buscar Paciente"  onkeyup="buscaPaciente(this)" required>
            <datalist id="pacientes"></datalist>
          </div>
          <div class="form-group">
            <label for="idProcedimento">Procedimento</label>
            <input type="search" id="idProcedimento"  list="procedimentos" class="form-control" placeholder="Buscar Procedimento"  onkeyup="buscaProcedimento(this)" required>
            <datalist id="procedimentos"></datalist>
          </div>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-success" value="Add">
        </div>
      </form>
    </div>
  </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteAutorizacaoModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form onsubmit="javascript:excluirAutorizacao()">
        <div class="modal-header">
          <h4 class="modal-title">Remover Autorização</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <p>Tem certeza de que deseja excluir estes registros?</p>
          <p class="text-warning "><small>Essa ação não pode ser desfeita.</small></p>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" id="deleteAutorizacao" class="btn btn-success" value="Excluir">
        </div>
      </form>
    </div>
  </div>
</div>


</body>
</html>