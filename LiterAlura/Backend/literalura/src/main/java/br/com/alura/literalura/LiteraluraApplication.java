//package br.com.alura.literalura;
//
//import br.com.alura.literalura.main.Application;
//import br.com.alura.literalura.repository.AuthorRepository;
//import br.com.alura.literalura.repository.BooksRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
//import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
//import org.springframework.boot.jdbc.autoconfigure.DataSourceTransactionManagerAutoConfiguration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//@SpringBootApplication
//@EnableJpaRepositories("br.com.alura.literalura.repository")
//public class LiteraluraApplication implements CommandLineRunner {
//
//    @Autowired
//    BooksRepository repository;
//    @Autowired
//    AuthorRepository authorRepository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(LiteraluraApplication.class, args);
//	}
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        Application application = new Application("https://gutendex.com/books", repository, authorRepository);
//        application.run();
//    }
//}
