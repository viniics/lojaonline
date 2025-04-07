package com.lojaonline.lojaonline.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.lojaonline.lojaonline.dto.ProductDTO;
import com.lojaonline.lojaonline.service.ProductService;

@Component
public class InventoryMaker implements CommandLineRunner {

    private final ProductService productService;

    public InventoryMaker(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        productService.cadastrarProduto(new ProductDTO(1L, "Teclado Gamer", 199.99, 15));
        productService.cadastrarProduto(new ProductDTO(2L, "Ratazana do Mar", 1, 99));
        productService.cadastrarProduto(new ProductDTO(3L, "Camisa Selecao Brasileira", 9.99, 5));
        productService.cadastrarProduto(new ProductDTO(4L, "Nike Court Vision", 99, 401));
        productService.cadastrarProduto(new ProductDTO(5L, "Monitor 24POL", 299.99, 20));
        productService.cadastrarProduto(new ProductDTO(6L, "Whatsapp Premium", 2121, 50));
        productService.cadastrarProduto(new ProductDTO(7L, "Garrafa Termica Gamer Premium Pro Max", 79.99, 35));
        productService.cadastrarProduto(new ProductDTO(8L, "Notebook Sem Processador", 3999.99, 8));
        productService.cadastrarProduto(new ProductDTO(9L, "Computador Descomputado", 599.99, 12));
        productService.cadastrarProduto(new ProductDTO(10L, "Teclado Mecânico", 129.99, 25));
        System.out.println("- Banco de dados povoado!");
    }
}
