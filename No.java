public class No {

    private Musica musica;

    public No(Musica musica) {
        this.musica = musica;
    }

    public Musica getMusica() {
        return musica;
    }

    public int getId() {
        return musica.getId(); // critério de inserção/busca
    }
}
