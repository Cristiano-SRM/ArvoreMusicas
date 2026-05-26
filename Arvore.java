public class Arvore {

    private No no;
    private Arvore esquerda;
    private Arvore direita;

    public Arvore() { // árvore vazia
        this.no = null;
        this.esquerda = null;
        this.direita = null;
    }

    public Arvore(No no) { // árvore criada a partir de um nó
        this.no = no;
        this.esquerda = null;
        this.direita = null;
    }

    public boolean isEmpty() {
        return this.no == null;
    }

    // Metodo de inserção, recebe um nó (musica:id) como parametro, utiliza-se os metodos get da classe musica
    public void inserir(No novo) {
        if (isEmpty()) {
            this.no = novo; // árvore vazia: novo nó vira raiz
        } else {
            if (novo.getId() < this.no.getId()) { // vai para a esquerda
                if (this.esquerda == null) {
                    this.esquerda = new Arvore(novo);
                    System.out.println("[Inserido] ID " + novo.getId()
                            + " à esquerda de ID " + this.no.getId());
                } else {
                    this.esquerda.inserir(novo); // desce recursivamente
                }
            } else if (novo.getId() > this.no.getId()) { // vai para a direita
                if (this.direita == null) {
                    this.direita = new Arvore(novo);
                    System.out.println("[Inserido] ID " + novo.getId()
                            + " à direita de ID " + this.no.getId());
                } else {
                    this.direita.inserir(novo); // desce recursivamente
                }
            } else {
                System.out.println("[Aviso] ID " + novo.getId()
                        + " já existe na árvore. Inserção ignorada.");
            }
        }
    }


    // Busca por ID e retorna
    public Musica buscar(int id) {
        if (isEmpty()) {
            return null; // chegou numa subárvore vazia: não encontrou
        }

        if (id == this.no.getId()) {
            return this.no.getMusica(); // encontrou!
        } else if (id < this.no.getId()) {
            if (this.esquerda == null) return null;
            return this.esquerda.buscar(id);  // busca à esquerda
        } else {
            if (this.direita == null) return null;
            return this.direita.buscar(id);   // busca à direita
        }
    }
}
