package com.bringglobal.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/current-accounts")
public class TransactionsController {

  @GetMapping
  public Object getTransactions() {

    RestTemplate restTemplate = new RestTemplate();
    Object result = restTemplate.getForObject("https://5f4ce45eeeec51001608e559.mockapi.io/api/transactions", Object.class);

    if (result instanceof List) {
      return result;
    }

    return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{type}")
  public Object getTransactionsPerType(@PathVariable final String type) {

    RestTemplate restTemplate = new RestTemplate();
    Object result = restTemplate.getForObject("https://5f4ce45eeeec51001608e559.mockapi.io/api/transactions", Object.class);

    if (result instanceof List) {
      return ((List<LinkedHashMap<String, String>>) result)
          .stream()
              .filter(t -> t.get("transactionType").equalsIgnoreCase(type))
              .collect(Collectors.toList());
    }

    return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{type}/amount")
  public Object getTransactionAmountPerType(@PathVariable final String type) {

    RestTemplate restTemplate = new RestTemplate();
    Object result = restTemplate.getForObject("https://5f4ce45eeeec51001608e559.mockapi.io/api/transactions", Object.class);

    if (result instanceof List) {
      AtomicReference<Double> total = new AtomicReference<>(0D);
      ((List<LinkedHashMap<String, String>>) result)
        .stream()
        .filter(t -> t.get("transactionType").equalsIgnoreCase(type))
        .forEach(t -> {
            Double amount = Double.parseDouble(String.valueOf(t.get("transactionAmount")));
          total.updateAndGet(v -> v + amount);
        });

      Map<String, Object> map = new HashMap<>();
      map.put("transactionType", type);
      map.put("totalAmount", total.get());

      return ResponseEntity.ok(map);
    }

    return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
  }
}
