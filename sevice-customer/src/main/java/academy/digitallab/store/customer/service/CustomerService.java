package academy.digitallab.store.customer.service;/*
 * @Autor: Alfonso Médicis
 * el 8/06/2020
 */

import academy.digitallab.store.customer.repository.entity.Customer;
import academy.digitallab.store.customer.repository.entity.Region;

import java.util.List;

public interface CustomerService {

    public List<Customer> findCustomerAll();
    public List<Customer> findByRegion(Region region);
    public Customer createCustumer( Customer customer);
    public Customer updateCustumer( Customer customer);
    public Customer deleteCustomer ( Customer customer);
    public Customer getCustumer( Long id);

}
