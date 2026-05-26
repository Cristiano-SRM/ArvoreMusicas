import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        Scanner scanner = new Scanner(System.in);

 // -------------------------------------------------------------------------------------------------------

        // Leitura do arquivo e montagem da árvore
        System.out.println("Lendo o arquivo .txt");
        String caminhoArquivo = "musicas.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine(); // primeira linha: total de músicas (ignorada)
            int total = Integer.parseInt(linha.trim());
            System.out.println("Total de músicas no arquivo: " + total);
            System.out.println();

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                // Formato: id;titulo;artista;duracao

                String[] partes = linha.split(";");
                if (partes.length != 4) {
                    System.out.println("[Aviso] Linha ignorada (formato inválido): " + linha);
                    continue; }
                
                int id          = Integer.parseInt(partes[0].trim());
                String titulo   = partes[1].trim();
                String artista  = partes[2].trim();
                double duracao  = Double.parseDouble(partes[3].trim());

                // divide o conteúdo de cada linha em 4 subvetores e armazenas os atributos em variaveis tipo Musica
                // nova variavel tipo musica é inserida na arvore que à ordenada pelo seu ID

                Musica musica = new Musica(id, titulo, artista, duracao);
                arvore.inserir(new No(musica));
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        System.out.println();
        System.out.println("=== Árvore montada com sucesso! ===");
        
 // -------------------------------------------------------------------------------------------------------
        
        // Menu interativo

        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("===OPÇÕES===");
            System.out.println("1 - Buscar música por ID");
            System.out.println("2 - Cadastrar nova música");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // Verifica se opcao é valida (um numero)
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }
           
            // Switch que pega a opção e direciona (chama o metodo relevante passando os parametros)
            switch (opcao) {
                case 1 -> buscarMusica(arvore, scanner);
                case 2 -> cadastrarMusica(arvore, scanner);
                case 0 -> System.out.println("Programa finalizado.");
                default -> System.out.println("Opção não reconhecida. Tente novamente.");
            }
        }

        scanner.close();
    }

//-------------------------------------------------------------------------------------------------------

    // Busca por ID
    private static void buscarMusica(Arvore arvore, Scanner scanner) {
        System.out.print("Digite o ID da música: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Musica encontrada = arvore.buscar(id);
            if (encontrada != null) {
                System.out.println("Música encontrada:");
                encontrada.exibir();
            } else {
                System.out.println("Nenhuma música encontrada com ID " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite apenas números.");
        }
    }


    // Cadastro de nova música
    private static void cadastrarMusica(Arvore arvore, Scanner scanner) {
        System.out.println("--- Cadastro de Nova Música ---");
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            // verifica duplicata antes de pedir os outros dados
            if (arvore.buscar(id) != null) {
                System.out.println("Já existe uma música com ID " + id + ". Cadastro cancelado.");
                return;
            }

            System.out.print("Título: ");
            String titulo = scanner.nextLine().trim();

            System.out.print("Artista: ");
            String artista = scanner.nextLine().trim();

            System.out.print("Duração (minutos, ex: 3.45): ");
            double duracao = Double.parseDouble(scanner.nextLine().trim());

            Musica novaMusica = new Musica(id, titulo, artista, duracao);
            arvore.inserir(new No(novaMusica));
            System.out.println("Música cadastrada com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Verifique ID e duração.");
        }
    }
}
