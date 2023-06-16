var autorizacaoSelect;

$(document).ready(function () {

    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
    });
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });

    busca();
});

cadAutorizacao = function (form) {
    event.preventDefault();
    var autorizacao = new Object();
    const msg = document.querySelector('#msg');

    var idProcedimentoElement = form.querySelectorAll("#idProcedimento")[0];
    var idPacienteElement = form.querySelectorAll("#idPaciente")[0];

    autorizacao.idProcedimento = idProcedimentoElement.list.options[0].getAttribute("data-id");
    autorizacao.idPaciente = idPacienteElement.list.options[0].getAttribute("data-id");
    autorizacao.nomeProcedimento = idProcedimentoElement.list.options[0].getAttribute("value");
    autorizacao.nomePaciente = idPacienteElement.list.options[0].getAttribute("value");

    var campoNomePaciente = $("#idPaciente").val();
    var campoNomeProcedimento = $("#idProcedimento").val();

    if (campoNomePaciente !== autorizacao.nomePaciente || campoNomeProcedimento !== autorizacao.nomeProcedimento) {
        msg.innerHTML = "Paciente ou procedimento inválido!";
        setTimeout(function () {
            msg.setAttribute('class', 'msg error');
        }, 10);
        return;
    }
    var cfg = {
        url: "autorizacao/cadastro",
        data: JSON.stringify(autorizacao),
        success: function (succJson) {
            form.submit();
        },
        error: function (errJson) {
            msg.innerHTML = errJson.responseText;
            setTimeout(function () {
                msg.setAttribute('class', 'msg error');
            }, 10);
        }
    };
    //time out para ocultar a mensagem
    setTimeout(function () {
        msg.classList.remove('error');
        msg.classList.remove('success');
        msg.innerHTML = "";
    }, 5000)
    controlecadastro.ajax.post(cfg);
};

busca = function () {
    var cfg = {
        type: "GET",
        url: "autorizacao/consulta",
        success: function (listaDeAutorizacoes) {
            visualizarAutorizacoes(listaDeAutorizacoes);
        },
        error: function (errJson) {
            resp = ("Erro ao buscar os dados!");
            exibirMessagem(resp, 2);
        }
    };
    controlecadastro.ajax.post(cfg);
};

visualizarAutorizacoes = function (listaDeAutorizacoes) {

    var tbody = $('#tbAutorizacoes > tbody');

    if (listaDeAutorizacoes != undefined) {
        if (listaDeAutorizacoes.length > 0) {
            for (var i = 0; i < listaDeAutorizacoes.length; i++) {
                tbody.append($('<tr>')
                    .append($('<td>').append(listaDeAutorizacoes[i].procedimento.nome))
                    .append($('<td>').append(listaDeAutorizacoes[i].paciente.nome))
                    .append($('<td>').append(formatarData(listaDeAutorizacoes[i].data)))
                    .append($('<td>').append(listaDeAutorizacoes[i].autorizado ? "Sim" : "Não"))
                    .append($('<td>').append("<a href='#deleteAutorizacaoModal' onclick = 'selectAutorizacaoExcluir(" + listaDeAutorizacoes[i].id + ")' class='delete' data-toggle='modal'><i class='material-icons' data-toggle='tooltip' title='Delete'>&#xE872;</i></a>"))
                )
            }
        } else {
            tbody.append($('<tr>')
                .append("<tr>Nenhum registro encontrado</tr>")
            )
        }

    } else {
        tbody.append($('<tr>')
            .append("<tr>Nenhum registro encontrado</tr>")
        )
    }
};

buscarAutorizacaoPorId = function (id) {
    var cfg = {
        type: "POST",
        url: "autorizacao/consultaPorId" + id,
        success: function (fornecedor) {
            $("#editName").val(fornecedor.name);
            $("#editComment").val(fornecedor.comment);
            $("#editEmail").val(fornecedor.email);
            $("#editCnpj").val(cnpjMask(fornecedor.cnpj));
            $("#editId").val(fornecedor.id);
        },
        error: function (err) {
            alert("Erro ao editar o fornecedor!" + err.responseText);
        }
    };
    controlecadastro.ajax.post(cfg);
};

buscaPaciente = function (elePaciente) {
    var paciente = new Object();
    paciente.nomePaciente = elePaciente.value;
    var cfg = {
        url: "paciente/consultaPorNome",
        data: JSON.stringify(paciente),
        success: function (paciente) {
            var dataList = document.getElementById("pacientes");
            dataList.innerHTML = "";
            paciente.forEach(function (paciente) {
                var option = document.createElement("option");
                option.value = paciente.nome;
                option.innerText = paciente.nome;
                option.setAttribute("data-id", paciente.id);
                dataList.appendChild(option);
            });
        },
        error: function (errJson) {
            resp = ("Erro ao buscar paciente!");
        }
    };
    controlecadastro.ajax.post(cfg);
};

buscaProcedimento = function (eleProcedimento) {
    var procedimento = new Object();
    procedimento.nomeProcedimento = eleProcedimento.value;
    var cfg = {
        url: "procedimento/consultaPorNome",
        data: JSON.stringify(procedimento),
        success: function (procedimento) {
            var dataList = document.getElementById("procedimentos");
            dataList.innerHTML = "";
            procedimento.forEach(function (procedimento) {
                var option = document.createElement("option");
                option.setAttribute("data-id", procedimento.id);
                option.value = procedimento.nome;
                dataList.appendChild(option);
            });
        },
        error: function (errJson) {
            resp = ("Erro ao buscar procedimento!");
        }
    };
    controlecadastro.ajax.post(cfg);
};

selectAutorizacaoExcluir = function (id) {
    autorizacaoSelect = id;
};

excluirAutorizacao = function () {
    var autorizacao = new Object();
    autorizacao.idAutorizacao = autorizacaoSelect;

    var cfg = {
        url: "autorizacao/excluir",
        data: JSON.stringify(autorizacao),
        success: function (data) {
            busca();
        },
        error: function (errJson) {
            alert("Erro ao excluir o fornecedor!" + err.responseText);
        }
    };
    controlecadastro.ajax.post(cfg);
};

mascaraCpf = function (id) {
    $("#" + id).mask("999.999.999-99");
};

function formatarData(timestamp) {
    var data = new Date(timestamp);
    var dia = adicionarZeroParaData(data.getDate());
    var mes = adicionarZeroParaData(data.getMonth() + 1);
    var ano = data.getFullYear();
    var hora = adicionarZeroParaData(data.getHours());
    var minuto = adicionarZeroParaData(data.getMinutes());
    var segundo = adicionarZeroParaData(data.getSeconds());

    var dataFormatada = dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto + ":" + segundo;
    return dataFormatada;
}

function adicionarZeroParaData(numero) {
    if (numero < 10) {
        return "0" + numero;
    }
    return numero;
}