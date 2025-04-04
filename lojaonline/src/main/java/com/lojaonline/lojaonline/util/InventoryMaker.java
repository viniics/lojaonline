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
        productService.cadastrarProduto(new ProductDTO("Teclado Gamer", 199.99, 15));
        productService.cadastrarProduto(new ProductDTO("Ratazana do Mar", 1, 99));
        productService.cadastrarProduto(new ProductDTO("Camisa Selecao Brasileira", 9.99, 5));
        productService.cadastrarProduto(new ProductDTO("Nike Court Vision", 99, 401));
        System.out.println("- Banco de dados povoado!");
    }
}
