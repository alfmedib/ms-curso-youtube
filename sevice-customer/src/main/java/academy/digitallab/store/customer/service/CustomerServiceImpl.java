package academy.digitallab.store.customer.service;/*
 * @Autor: Alfonso Médicis
 * el 8/06/2020
 */

import academy.digitallab.store.customer.repository.CustomerRepository;
import academy.digitallab.store.customer.repository.entity.Customer;
import academy.digitallab.store.customer.repository.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustumer(Customer customer) {

        Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());

        if( customerDB != null){
            return  customerDB;
        }

        customer.setState("CREATED");
        customerDB = customerRepository.save(customer);

        return customerDB;
    }

    @Override
    public Customer updateCustumer(Customer customer) {

        Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());

        if( customerDB == null){
            return  null;
        }

        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());

        return customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {

        Customer customerDB = getCustumer(customer.getId());

        if( customerDB == null){
            return  null;
        }

        customerDB.setState("DELETED");
        return customerRepository.save(customerDB);
    }

    @Override
    public Customer getCustumer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
