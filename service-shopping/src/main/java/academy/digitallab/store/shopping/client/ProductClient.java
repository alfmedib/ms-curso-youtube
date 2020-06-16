package academy.digitallab.store.shopping.client;/*
 * @Autor: Alfonso Médicis
 * el 15/06/2020
 */

import academy.digitallab.store.shopping.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-product")
@RequestMapping(value = "/products")
public interface ProductClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStokProduct(@PathVariable("id") Long id,
                                                     @RequestParam(name = "quantity", required = true)
                                                     Double quantity);
}
