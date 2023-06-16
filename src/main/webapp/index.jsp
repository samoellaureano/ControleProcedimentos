<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Controle de Procedimentos</title>
  <style>
    body {
      padding-top: 56px; /* altura do menu */
    }
    .sidebar {
      position: fixed;
      top: 0;
      left: 0;
      width: 250px;
      height: 100vh;
      background-color: #f8f9fa;
    }
    .content {
      margin-left: 250px;
      padding: 20px;
    }
  </style>
</head>
<body>

<div class="sidebar">
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav flex-column">
        <li class="nav-item">
          <a class="nav-link" href="#">Item 1</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Item 2</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Item 3</a>
        </li>
      </ul>
    </div>
  </nav>
</div>
<div class="content">
  <h1>Conteúdo da Página</h1>
  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ullamcorper nulla sit amet feugiat cursus. Nulla facilisi. Mauris mattis efficitur malesuada. Proin pulvinar mi eget erat condimentum semper.</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>