package br.com.alura.literalura.main;

import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.model.BookData;
import br.com.alura.literalura.model.Book;
import br.com.alura.literalura.model.Results;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BooksRepository;
import br.com.alura.literalura.service.ApiConection;
import br.com.alura.literalura.service.DataConverter;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE;
    private ApiConection conection = new ApiConection();
    private DataConverter converter = new DataConverter();
    private String json;
    private String bookName;
    private String bookAuthor;
    private Optional<Book> bookSearch;
    private List<Book> booksInDB = new ArrayList<>();
    private List<Author> authorsInDB = new ArrayList<>();
    private BooksRepository repository;
    private AuthorRepository authorRepository;



    public Application(String urlBase, BooksRepository repository, AuthorRepository authorRepository) {
        URL_BASE = urlBase;
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    // opções de consulta de dados a API Guntendex para cadastro de livros e autores no banco 
    private void menuApi(){
        String textMenu = """
                    ---------- Bem vindo ao LiterAlura -------------
                    Opções para consulsa da API Gutendex e cadastro dos dados no Banco de Dados
                    1- Buscar e cadastrar um novo livro
                    2- Cadastrar todos os livros de um autor;
                    0- Sair
                """;
        System.out.println(textMenu);

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                findBooksInApi();
                break;
            case 2:
                savedBooksFromApiAuthors();
                break;
            case 0:
                System.out.println("Saindo..");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    // opções de consulta ao banco de dados
    private void menuRepo(){
        String textMenu = """
                    ---------- Bem vindo ao LiterAlura ----------
                    1- Buscar livro por título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- listar autores vivos em um determinado ano
                    5- listar livros por idioma
                    0- Sair
                """;
        System.out.println(textMenu);

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                findBooksByTitle();
                break;
            case 2:
                listSavedBooks();
                break;
            case 3:
                listSavedAuthors();
                break;
            case 4:
                findAliveAuthorsByYear();
                break;
            case 5:
                listBooksByLanguage();
                break;
            case 0:
                System.out.println("Saindo..");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    public void run () {
        String textMenu = """
                    ---------- Bem vindo ao LiterAlura -------------
                    1- Opções API Gutendex
                    2- Opções de busca no Banco de Dados
                    0- Sair
                """;
        var opcao = -1;
        while (opcao != 0) {
            System.out.println(textMenu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    menuApi();
                    break;
                case 2:
                    menuRepo();
                    break;
                case 0:
                    System.out.println("Até a próxima");
                    break;
                default:
                    System.out.println("Opção inválida");

            }
        }
    }

    //-------------------------------------------------------------- métodos do menu da API

    private void findBooksInApi() {
        setJson();
        BookData bookData = getBookData(bookName);
        if (bookData != null && bookData.title() != null) {
            Book book = new Book(bookData);
            System.out.println(book);
            saveBook(book);
        }else{
            System.out.println("Livro não encontrado!");
        }

    }

    private void setJson(){
        System.out.println("Responda para busca as perguntas a seguir e deixe em branco os campos que não são de interesse");
        System.out.println("Digite trecho do nome do livro: ");
        this.bookName = scanner.nextLine();
        System.out.println("Digite o nome do author: ");
        this.bookAuthor = scanner.nextLine();
        //url de busca
        String url = URL_BASE + "?search=" + bookAuthor + "%20"+ bookName.replace(" ", "+");
        System.out.println(url);
        json = conection.getData(url);
    }

    private BookData getBookData(String bookName) {
        Results bookDataResults = converter.getData(json, Results.class);
        return bookDataResults.results().stream()
                .filter(book -> book.title().toLowerCase().contains(bookName.toLowerCase()))
                .findFirst().orElse(null);
    }

    private void saveBook(Book book) {
        Optional<Book> bookInDB = repository.findByTitleIgnoreCase(book.getTitle());
        if (bookInDB.isPresent()) {
            System.out.println("Livro cadastrado previamente na base de dados");
            return;
        }

        List<Author> verifidAuthorsDB = book.getAuthors().stream()
                .map(author -> authorRepository.findByNameIgnoreCase(author.getName())
                        .orElseGet(() -> authorRepository.save(author))).collect(Collectors.toList());

        book.setAuthors(verifidAuthorsDB);
        repository.save(book);
        System.out.println("Livro cadastrado com sucesso!");

    }


    private void savedBooksFromApiAuthors() {
        setJson();
        Results bookDataResults = converter.getData(json, Results.class);
        bookDataResults.results().forEach(System.out::println);
        bookDataResults.results().stream()
                .filter(Objects::nonNull)
                .map(bookData -> new Book(bookData))
                .forEach(book -> saveBook(book));
    }

    //------------------------------------------------------------------- métodos do menu do banco de dados

    private void findBooksByTitle() {
        System.out.println("Digite o nome do livro: ");
        bookName = scanner.nextLine();
        Optional<Book> bookInDB = repository.findByTitleContainingIgnoreCase(bookName);
        if (bookInDB.isPresent()) {
            var book = bookInDB.get();
            System.out.println(book);
        }else{
            System.out.println("Livro não foi encontrado na base de dados");
        }
    }

    private void listSavedBooks() {
        booksInDB = repository.findAll();
        if (booksInDB != null) {
            booksInDB.forEach(book -> System.out.println(book));
        }else{
            System.out.println("Nenhum livro encontrado!");
        }

    }

    private void listSavedAuthors() {
        authorsInDB = authorRepository.findAll();
        if (authorsInDB != null) {
            authorsInDB.forEach(author -> System.out.println(author));
        }else {
            System.out.println("Nenhum author cadastrado!");
        }
    }

    private void findAliveAuthorsByYear() {
        System.out.println("Digite o ano limite para busca: ");
        var seachYear = scanner.nextInt();
        scanner.nextLine();
        authorsInDB = authorRepository.findAliveAuthorInYear(seachYear);
        if (!authorsInDB.isEmpty()){
            authorsInDB.forEach(author -> System.out.println(author));
        }else{
            System.out.println("Nenhum author vivo cadastrado na base de dado");
        }
    }

    private void listBooksByLanguage() {
        System.out.println("Digite a lingua em que deseja fazer a busca (ex: en): ");
        var language = scanner.nextLine();
        booksInDB = repository.findByLanguagesContainingIgnoreCase(language);
        if (!booksInDB.isEmpty()) {
            booksInDB.forEach(book -> System.out.println(book));
        }else{
            System.out.println("Nenhum livro encontrado!");
        }
    }

}
