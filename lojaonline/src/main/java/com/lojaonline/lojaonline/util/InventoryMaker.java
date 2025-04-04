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
        productService.cadastrarProduto(new ProductDTO(1L,"Teclado Gamer", 199.99, 15));
        productService.cadastrarProduto(new ProductDTO(2L,"Ratazana do Mar", 1, 99));
        productService.cadastrarProduto(new ProductDTO(3L,"Camisa Selecao Brasileira", 9.99, 5));
        productService.cadastrarProduto(new ProductDTO(40L,"Nike Court Vision", 99, 401));
        System.out.println("- Banco de dados povoado!");
    }
}
