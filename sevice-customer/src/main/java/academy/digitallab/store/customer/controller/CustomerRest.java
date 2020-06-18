package academy.digitallab.store.customer.controller;/*
 * @Autor: Alfonso Médicis
 * el 8/06/2020
 */

import academy.digitallab.store.customer.repository.entity.Customer;
import academy.digitallab.store.customer.repository.entity.Region;
import academy.digitallab.store.customer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerRest {

    @Autowired
    CustomerService customerService;

    // ********************* Encutra a los clientes ******************************
    @GetMapping
   public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam(name = "regionId", required = false) Long regionId){

       List<Customer> customers = new ArrayList<>();

        if(null == regionId){
            customers = customerService.findCustomerAll();
            if(customers.isEmpty()){
                return ResponseEntity.noContent().build();
            }

        }else{
            Region region = new Region();
            region.setId(regionId);

            customers = customerService.findByRegion(region);

            if(null == customers){
                log.error("Cliente con id de región {} no se encuentra ", regionId);
                return  ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(customers);
    }

    // ******************** Buscar Cliente por id *******************************

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){

        log.info("Buscar al ciente con id ", id);

        Customer customer = customerService.getCustumer(id);
        if(null == customer){
            log.info("Cliente con id {} no se encuntra ", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);

    }

    // ******************** Crear cliente *******************************

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        log.info("Creando al cliente: {}", customer);

        if(result.hasErrors()){
            log.info("ERROR: {}", customer);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessege(result));

        }

        Customer customerBD = customerService.createCustumer(customer);

            log.info("Se a HA CREADO {}", customerBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerBD);
    }

    // *********************** Actualizar Cliente ****************************

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){

        log.info("Actualizando cliente con el id {}", id);

        Customer currenCustomer = customerService.getCustumer(id);

        if(null == currenCustomer){
            log.info("Incapaz de actualizar cliente con id: {}, id no se encuentra", id);
            return ResponseEntity.notFound().build();
        }

        customer.setId(id);
        currenCustomer = customerService.updateCustumer(customer);
        log.info(" SE actualizado {}", currenCustomer);
        return ResponseEntity.ok(currenCustomer);
    }

    // ********************* Borrar un cliente ******************************

    public ResponseEntity<Customer> deleteCustomer(Long id){
        log.info("Buscando y borrando cliente con el id {}", id);

        Customer customer = customerService.getCustumer(id);

        if(null == customer){
            log.info("Incapaz de borrar Cliente. Cliente con id {} no se encuentra", id);
            return ResponseEntity.notFound().build();
        }

        customer = customerService.deleteCustomer(customer);

        return ResponseEntity.ok(customer);
    }

    // ******************** Mensajes  de validación en String *******************************

    private String formatMessege( BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return  error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        // ***************** convertir a json *******************

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(errorMessage);

        }catch (JsonProcessingException e){

            e.printStackTrace();

        }

        return jsonString;
    }
}
