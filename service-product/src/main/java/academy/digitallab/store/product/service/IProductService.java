package academy.digitallab.store.product.service;/*
 * Creado por  @Autor: Alfonso Médicis
 * el 5/06/2020
 */

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;

import java.util.List;

public interface IProductService {

    public List<Product> listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(Long id);
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id, Double quality);
}
