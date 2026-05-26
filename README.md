# ArvoreMusicas

O programa lê um arquivo .txt contendo um catalogo de musicas e insere em uma arvore de busca binaria, a cada inserção o algoritmo compara o ID do novo elemento com o nó atual, IDs menores seguem para a subarvore esquerda e IDs maiores seguem para a subarvore direita.

Apos montada a arvore, usuario tem as opções buscar uma musica por ID ou cadastrar novas musicas.

```Para executar:
javac *.java
java Main
```

O arquivo `musicas.txt` deve estar na mesma pasta dos arquivos compilados.

## Metodos:
**inserir(No novo)** - Percorre a arvore recursivamente comparando IDs até encontrar uma subarvore vazia, onde o novo nó é inserido.

**buscar(int id)** - a cada nó visitado, compara o ID buscado: se igual, retorna musica, se menor desce à esquerda; se maior desce a direita.