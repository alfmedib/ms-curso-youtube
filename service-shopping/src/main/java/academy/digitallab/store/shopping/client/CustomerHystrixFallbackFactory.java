package academy.digitallab.store.shopping.client;/*
 * @Autor: Alfonso Médicis
 * el 15/06/2020
 */

import academy.digitallab.store.shopping.model.Customer;
import org.springframework.http.ResponseEntity;

public class CustomerHystrixFallbackFactory implements CustomerClient {
    @Override
    public ResponseEntity<Customer> getCustomer(Long id) {
        Customer customer = Customer.builder()
                .firstName("none")
                .lastName("none")
                .email("none")
                .photoUrl("none").build();
        return ResponseEntity.ok(customer);
    }
}
