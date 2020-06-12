package academy.digitallab.store.product.controller;/*
 * @Autor: Alfonso Médicis
 * el 6/06/2020
 */

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter @Setter @Builder
public class ErrorMessage {

    private String code;
    private List<Map<String, String>> mesages;
}
