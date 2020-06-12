package academy.digitallab.store.shopping.controller;/*
 * @Autor: Alfonso Médicis
 * el 11/06/2020
 */

import academy.digitallab.store.shopping.entity.Invoice;
import academy.digitallab.store.shopping.service.InvoiceService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceRest {

    @Autowired
    private InvoiceService invoiceService;

    // ********************** Busca todas las facturas *****************************

    @GetMapping
    public ResponseEntity<List<Invoice>> listAllInvoices(){

        List<Invoice> invoices = invoiceService.findAllInvoice();

        if(invoices.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(invoices);
    }

    // ********************** Buscar factura por id *****************************

    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice> findInvoiceById(@PathVariable("id") Long id){

        log.info("Encontrando factura por el id {}", id);

        Invoice invoice = invoiceService.getInvoice(id);

        if(invoice == null){
            log.error("La factura con el id {} no se encuentra", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(invoice);
    }

    // ******************** Crear factura *******************************

    @PostMapping
    public ResponseEntity<?> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result){

        log.info("Creando la factura {}", invoice);

        if(result.hasErrors()){
            log.info("ERROR: {}", invoice);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessages(result));

        }

        Invoice invoiceBD = invoiceService.createInvoice(invoice);
        log.info("Se ha CREADO la factura {}", invoiceBD);

        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceBD);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable("id") Long id, @RequestBody Invoice invoice){

        log.info("Actualizando factura con el id: {}", id);
        invoice.setId(id);
        Invoice currenInvoice = invoiceService.updateInvoice(invoice);

        if(currenInvoice == null){
            log.info("La actualización de factura con el id: {} no se encuentra", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(currenInvoice);

    }


    // ********************** Validaciones *****************************


    private String formatMessages(BindingResult result){

        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
            .code("01")
            .messages(errors).build();

        // ******************* Pasar a Json ********************************
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {

            jsonString = mapper.writeValueAsString(errorMessage);

        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return  jsonString;
    }


}
