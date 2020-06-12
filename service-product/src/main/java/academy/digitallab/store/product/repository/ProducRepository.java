package academy.digitallab.store.product.repository;/*
 * Creado por:  @Autor: Alfonso Médicis
 * el 4/06/2020
 */

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);
}
