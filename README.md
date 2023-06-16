# Controle de Autorizações de Procedimentos Médicos
Este projeto consiste em um sistema de controle de autorizações de procedimentos médicos para um plano de saúde. O sistema é responsável por verificar os critérios de idade e sexo de um paciente e determinar se a execução de um procedimento é permitida ou não. Caso o procedimento não esteja listado nas regras de autorização, ele é negado no cadastro.

# Backend
Linguagem: Java
Framework: JPA (Java Persistence API)
Framework ORM: Hibernate
Generalização de Classes: Utilizada para promover a reutilização de código através de classes abstratas e herança.
# Banco de Dados
Banco de Dados: MySQL 5.7
Utilização de JPA e Hibernate para mapeamento objeto-relacional e persistência dos dados.
# Servidor de Aplicação
Servidor de Aplicação: WildFly 20.0.1
# Frontend
Tecnologias: HTML, CSS, JavaScript
Requisições Assíncronas: Utilização do AJAX para realizar requisições assíncronas ao backend sem recarregar a página.
# Como executar o projeto
- Certifique-se de ter o JDK (Java Development Kit) instalado em sua máquina.
- Faça o download do servidor de aplicação WildFly versão 20.0.1 e realize a instalação/configuração.
- Instale o banco de dados MySQL 5.7 e configure as credenciais de acesso.
- Clone o repositório do projeto para sua máquina local.
- Abra o projeto em sua IDE preferida (por exemplo, Eclipse, IntelliJ IDEA).
- Importe o projeto como um projeto Maven.
- Configure as propriedades de conexão com o banco de dados no arquivo persistence.xml.
- Execute a aplicação no servidor WildFly.
- Acesse a aplicação no navegador para interagir com o frontend.
# Contribuição
Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou correções para o projeto, sinta-se à vontade para abrir uma issue ou enviar um pull request.

# Licença
Este projeto está licenciado sob a MIT License.
