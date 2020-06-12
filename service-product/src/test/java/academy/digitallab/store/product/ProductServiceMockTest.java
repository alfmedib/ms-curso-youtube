package academy.digitallab.store.product;/*
 * @Autor: Alfonso Médicis
 * el 5/06/2020
 */

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProducRepository;
import academy.digitallab.store.product.service.IProductService;
import academy.digitallab.store.product.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProducRepository producRepository;

    private IProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);

        productService = new ProductServiceImpl(producRepository);

        Product computer = Product.builder()
                .id(1l)
                .name("Computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("230"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(producRepository.findById(1L))
                .thenReturn(Optional.of(computer));

        Mockito.when(producRepository.save(computer))
                .thenReturn(computer);
    }

    @Test
    public void whenValidateGetIdThenReturnProduc(){
        Product found = productService.getProduct(1l);
        Assertions.assertThat(found.getName().equals("Computer"));
    }

    @Test
    public void  whenValidUpdateStock_thenReturnNewStock(){
            Product newStock = productService.updateStock(1L, Double.parseDouble("8"));

            Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }


}
