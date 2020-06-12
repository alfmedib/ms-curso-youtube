package academy.digitallab.store.product.service;/*
 * Creado por  @Autor: Alfonso Médicis
 * el 5/06/2020
 */

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProducRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor // se inyeta por constructor
public class ProductServiceImpl implements IProductService {

   // @Autowired // se inyeta por constructor
    private final ProducRepository producRepository;

    @Override
    public List<Product> listAllProduct() {
        return producRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return producRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {

        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return producRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        Product productDB = getProduct(product.getId());

        if(null == product){
            return null;
        }

        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());

        return producRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {

        Product productDB = getProduct(id);

        if(null == productDB){
            return null;
        }

        productDB.setStatus("DELETED");
        return  producRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return producRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quality) {

        Product productDB = getProduct(id);

        if(null == productDB){
            return null;
        }

        Double stock = productDB.getStock() + quality;
        productDB.setStock(stock);
        return producRepository.save(productDB);
    }
}
