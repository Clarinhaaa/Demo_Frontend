//* Pesquisar por ID
document
  .getElementById("botaoPesquisarPorId")
  .addEventListener("click", function (event) {
    event.preventDefault();
    const idLivro = document.getElementById("idLivro").value;

    if (!idLivro || isNaN(idLivro) || idLivro <= 0) {
      alert("ID inválido");
      return;
    }

    window.location.href = `/livro/getById/${idLivro}`;
  });

//* Pesquisar por título
document
  .getElementById("botaoPesquisarPorTitulo")
  .addEventListener("click", function (event) {
    event.preventDefault();
    const titulo = document.getElementById("titulo").value;

    if (!titulo || titulo === null) {
      alert("Título inválido");
      return;
    }

    window.location.href = `/livro/getByTitulo/${titulo}`;
  });

//* Pesquisar por ISBN
document
  .getElementById("botaoPesquisarPorIsbn")
  .addEventListener("click", function (event) {
    event.preventDefault();
    const isbn = document.getElementById("isbn").value;

    if (!isbn || isNaN(isbn) || isbn <= 0) {
      alert("ISBN inválido");
      return;
    }

    window.location.href = `/livro/getByIsbn/${isbn}`;
  });

//* Deletar livro
document
  .getElementById("botaoDeletarLivro")
  .addEventListener("click", function (event) {
    event.preventDefault();
    const id = this.getAttribute("data-id");

    if (confirm("Tem certeza que deseja deletar este livro?")) deleteLivro(id);
  });

function deleteLivro(id) {
  fetch(`/livro/${id}`)
    .then(() => {
      alert("Livro excluído com sucesso");
      location.reload();
    })
    .catch((error) => {
      console.error(error);
      alert("Erro ao excluir o livro");
    });
}

//* Cadastrar livro
document
  .getElementById("formCadastroLivro")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    const livro = {
      id_livro: null,
      titulo: document.getElementById("cadastroTitulo").value,
      autor: document.getElementById("cadastroAutor").value,
      editora: document.getElementById("cadastroEditora").value,
      ano_publicacao: document.getElementById("cadastroAnoPublicacao").value,
      genero: document.getElementById("cadastroGenero").value,
      isbn: document.getElementById("cadastroIsbn").value,
      num_paginas: document.getElementById("cadastroNumPaginas").value,
    };

    addLivro(livro);
  });

function addLivro(livro) {
  fetch(`/livro`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(livro),
  })
    .then(() => {
      alert("Livro cadastrado com sucesso");
      location.reload();
    })
    .catch((error) => {
      console.error(error);
      alert("Erro ao cadastrar o livro");
    });
}
