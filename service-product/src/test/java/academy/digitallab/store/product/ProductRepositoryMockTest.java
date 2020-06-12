package academy.digitallab.store.product;/*
 * Creado por  @Autor: Alfonso Médicis
 * el 5/06/2020
 */

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProducRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProducRepository producRepository;

    @Test
    public  void findByCategory_returnListProducts(){
        Product product001 = Product.builder()
                .name("Computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.45"))
                .status("create")
                .createAt(new Date()).build();

        producRepository.save(product001);

        List<Product> found = producRepository.findByCategory(product001.getCategory());

        Assertions.assertTrue(found.size() >= 2);
    }
}
