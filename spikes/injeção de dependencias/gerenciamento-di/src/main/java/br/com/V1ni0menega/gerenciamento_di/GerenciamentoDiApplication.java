package br.com.V1ni0menega.gerenciamento_di;

import br.com.V1ni0menega.gerenciamento_di.model.Produto;
import br.com.V1ni0menega.gerenciamento_di.service.ProdutoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciamentoDiApplication implements CommandLineRunner {

	private final ProdutoService produtoService;

	public GerenciamentoDiApplication(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoDiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("=== Demonstração de Injeção de Dependência ===");

		produtoService.cadastrar("Notebook", 3500.00);
		produtoService.cadastrar("Mouse", 50.00);
		produtoService.cadastrar("Teclado", 120.00);

		System.out.println("Produtos cadastrados via ProdutoService (injetado pelo Spring):");
		for (Produto produto : produtoService.listarTodos()) {
			System.out.println(" - " + produto);
		}
	}

}
