const apiUrl = "http://localhost:8080/api/movimentacoes";
const tabela = document.querySelector("#tabela-movimentacoes tbody");

async function carregarMovimentacoes() {
  try {
    const resposta = await fetch(apiUrl);
    const dados = await resposta.json();

    tabela.innerHTML = ""; // Limpa antes de preencher

    dados.forEach(mov => {
      const linha = document.createElement("tr");

      linha.innerHTML = `
        <td>${formatarData(mov.data)}</td>
        <td>${mov.descricao}</td>
        <td>R$ ${mov.valor.toFixed(2)}</td>
        <td>${mov.categoria}</td>
        <td>${mov.tipo}</td>
        <td>
          <button onclick="editarMov(${mov.id})">🖊</button>
          <button onclick="deletarMov(${mov.id})">🗑</button>
        </td>
      `;

      tabela.appendChild(linha);
    });
  } catch (erro) {
    console.error("Erro ao carregar movimentações:", erro);
  }
}

// Formatador de data simples
function formatarData(data) {
  const [ano, mes, dia] = data.split("-");
  return `${dia}/${mes}/${ano}`;
}

// Funções de ação (ainda não implementadas)
function editarMov(id) {
  alert(`Editar movimentação ID ${id} (em breve)`);
}

function deletarMov(id) {
  alert(`Excluir movimentação ID ${id} (em breve)`);
}

carregarMovimentacoes();
