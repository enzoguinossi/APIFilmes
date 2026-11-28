
// LISTA DE FILMES
let filmes = JSON.parse(localStorage.getItem("filmes")) || [];

let proximoId = Number(localStorage.getItem("proximoId")) || 1;

let filmeSelecionadoId = null;


// CAMPOS DO FORMULÁRIO
const campoId = document.getElementById("id");
const campoTitulo = document.getElementById("titulo");
const campoAno = document.getElementById("ano");
const campoGenero = document.getElementById("genero");
const campoDiretor = document.getElementById("diretor");
const campoIdioma = document.getElementById("idioma");
const campoDuracao = document.getElementById("duracao");
const campoClassificacao = document.getElementById("classificacao");
const campoNota = document.getElementById("nota");

const formulario = document.getElementById("formFilme");

const tabela = document.getElementById("listaFilmes");

const btnAlterar = document.getElementById("btnAlterar");
const btnExcluir = document.getElementById("btnExcluir");


// GUARDAR FILMES NO NAVEGADOR
function guardarFilmes() {

    localStorage.setItem("filmes", JSON.stringify(filmes));

    localStorage.setItem("proximoId", proximoId);

}


// CAPTURAR DADOS DO FORMULÁRIO
function capturarDados() {

    return {

        titulo: campoTitulo.value.trim(),

        ano: Number(campoAno.value),

        genero: campoGenero.value.trim(),

        diretor: campoDiretor.value.trim(),

        idioma: campoIdioma.value.trim(),

        duracao: Number(campoDuracao.value),

        classificacao: campoClassificacao.value,

        nota: Number(campoNota.value)

    };

}


// VALIDAR DADOS
function validarDados(dados) {

    if (!formulario.reportValidity()) {
        return false;
    }

    if (dados.ano < 1888 || dados.ano > 2100) {

        alert("Informe um ano válido.");

        campoAno.focus();

        return false;

    }

    if (dados.duracao <= 0) {

        alert("A duração deve ser maior que zero.");

        campoDuracao.focus();

        return false;

    }

    if (dados.nota < 0 || dados.nota > 10) {

        alert("A nota deve estar entre 0 e 10.");

        campoNota.focus();

        return false;

    }

    return true;

}


// CALCULAR DURAÇÃO EM HORAS
function calcularDuracao(minutos) {

    const horas = Math.floor(minutos / 60);

    const restante = minutos % 60;

    return `${horas}h ${restante}min`;

}


// CALCULAR AVALIAÇÃO
function calcularAvaliacao(nota) {

    if (nota >= 8) {

        return "Excelente";

    }

    if (nota >= 6) {

        return "Bom";

    }

    return "Regular";

}


// SALVAR FILME
document.getElementById("btnSalvar").addEventListener("click", function() {

    const dados = capturarDados();

    if (!validarDados(dados)) {
        return;
    }

    const dataAtual = new Date().toLocaleDateString("pt-BR");

    const filme = {

        id: proximoId++,

        ...dados,

        avaliacao: calcularAvaliacao(dados.nota),

        criadoEm: dataAtual,

        atualizadoEm: "-"

    };

    filmes.push(filme);

    guardarFilmes();

    atualizarTabela();

    limparFormulario();

    alert("Filme cadastrado com sucesso!");

});


// ALTERAR FILME
btnAlterar.addEventListener("click", function() {

    if (filmeSelecionadoId === null) {

        alert("Selecione um filme na tabela!");

        return;

    }

    const dados = capturarDados();

    if (!validarDados(dados)) {
        return;
    }

    const indice = filmes.findIndex(
        filme => filme.id === filmeSelecionadoId
    );

    if (indice === -1) {
        return;
    }

    filmes[indice] = {

        ...filmes[indice],

        ...dados,

        avaliacao: calcularAvaliacao(dados.nota),

        atualizadoEm: new Date().toLocaleDateString("pt-BR")

    };

    guardarFilmes();

    atualizarTabela();

    limparFormulario();

    alert("Filme alterado com sucesso!");

});


// EXCLUIR FILME
btnExcluir.addEventListener("click", function() {

    if (filmeSelecionadoId === null) {

        alert("Selecione um filme na tabela!");

        return;

    }

    const confirmar = confirm(
        "Deseja realmente excluir este filme?"
    );

    if (!confirmar) {
        return;
    }

    filmes = filmes.filter(
        filme => filme.id !== filmeSelecionadoId
    );

    guardarFilmes();

    atualizarTabela();

    limparFormulario();

    alert("Filme excluído com sucesso!");

});


// SELECIONAR FILME NA TABELA
function selecionarFilme(id) {

    const filme = filmes.find(
        item => item.id === id
    );

    if (!filme) {
        return;
    }

    filmeSelecionadoId = id;

    campoId.value = filme.id;

    campoTitulo.value = filme.titulo;

    campoAno.value = filme.ano;

    campoGenero.value = filme.genero;

    campoDiretor.value = filme.diretor;

    campoIdioma.value = filme.idioma;

    campoDuracao.value = filme.duracao;

    campoClassificacao.value = filme.classificacao;

    campoNota.value = filme.nota;

    btnAlterar.disabled = false;

    btnExcluir.disabled = false;

    atualizarTabela();

}


// ATUALIZAR TABELA
function atualizarTabela() {

    tabela.innerHTML = "";

    // CAPTURAR FILTROS
    const tituloFiltro = document.getElementById("filtroTitulo").value.toLowerCase();

    const generoFiltro = document.getElementById("filtroGenero").value.toLowerCase();

    const anoFiltro = document.getElementById("filtroAno").value;

    const diretorFiltro = document.getElementById("filtroDiretor").value.toLowerCase();

    const duracaoFiltro = document.getElementById("filtroDuracao").value;


    // FILTRAR FILMES
    const filmesFiltrados = filmes.filter(filme => {

        return filme.titulo.toLowerCase().includes(tituloFiltro)

            && filme.genero.toLowerCase().includes(generoFiltro)

            && (!anoFiltro || String(filme.ano) === anoFiltro)

            && filme.diretor.toLowerCase().includes(diretorFiltro)

            && (!duracaoFiltro || filme.duracao <= Number(duracaoFiltro));

    });


    // VERIFICAR SE EXISTEM FILMES
    if (filmesFiltrados.length === 0) {

        const linha = document.createElement("tr");

        const celula = document.createElement("td");

        celula.colSpan = 13;

        celula.className = "tabela-vazia";

        celula.textContent = "Nenhum filme cadastrado ou encontrado.";

        linha.appendChild(celula);

        tabela.appendChild(linha);

        return;

    }


    // MOSTRAR FILMES
    filmesFiltrados.forEach(filme => {

        const linha = document.createElement("tr");

        if (filme.id === filmeSelecionadoId) {

            linha.classList.add("selecionado");

        }

        const valores = [

            filme.id,

            filme.titulo,

            filme.ano,

            filme.genero,

            filme.diretor,

            filme.idioma,

            filme.duracao,

            calcularDuracao(filme.duracao),

            filme.classificacao,

            filme.nota.toFixed(1),

            filme.avaliacao,

            filme.criadoEm,

            filme.atualizadoEm

        ];


        valores.forEach(valor => {

            const celula = document.createElement("td");

            celula.textContent = valor;

            linha.appendChild(celula);

        });


        // CLICAR NA LINHA PARA SELECIONAR
        linha.addEventListener("click", function() {

            selecionarFilme(filme.id);

        });

        tabela.appendChild(linha);

    });

}


// LIMPAR FORMULÁRIO
function limparFormulario() {

    formulario.reset();

    campoId.value = "";

    filmeSelecionadoId = null;

    btnAlterar.disabled = true;

    btnExcluir.disabled = true;

    atualizarTabela();

}


// BOTÃO LIMPAR
document.getElementById("btnLimpar").addEventListener("click", function() {

    limparFormulario();

});


// PESQUISAR FILMES
document.querySelectorAll(".filtros input").forEach(campo => {

    campo.addEventListener("input", atualizarTabela);

});


// LIMPAR FILTROS
document.getElementById("btnLimparFiltros").addEventListener("click", function() {

    document.querySelectorAll(".filtros input").forEach(campo => {

        campo.value = "";

    });

    atualizarTabela();

});


// CARREGAR TABELA AO ABRIR A PÁGINA
atualizarTabela();
