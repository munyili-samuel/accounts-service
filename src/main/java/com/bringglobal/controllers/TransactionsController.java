package com.bringglobal.controllers;

import com.bringglobal.services.RESTService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import models.Transaction;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/transactions")
public class TransactionsController {
  private final static Logger logger = Logger.getLogger(TransactionsController.class);
  private final RESTService restService;
  private static Map<String, String> map = new HashMap<>();
  static {
    map.put("error", "Unexpected response structure received from Open Bank Project");
  }

  //TODO: This URL will be moved to application properties
  private final String url = "https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions";

  public TransactionsController(RESTService restService) {
    this.restService = restService;
  }

  @GetMapping
  public Object getTransactions() {
    try {
      return restService.getTransactions(url).orElse(Collections.EMPTY_LIST);
    } catch (Exception e) {
     return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Gets a list all transactions of a certain transaction type.
   * Returns an empty list if not matches
   *
   * @param type: String
   *
   * @return ResponseEntity
   */
  @GetMapping("/{type}")
  public Object getTransactionsPerType(@PathVariable final String type) {
    logger.info("Retrieving transactions of type " + type);

    List<Transaction> transactions;
    try {
      transactions = restService.getTransactions(url).orElse(Collections.EMPTY_LIST);
    } catch (Exception e) {
      return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return transactions.stream()
     .filter(tr -> tr.getTransactionType().equalsIgnoreCase(type)).collect(Collectors.toList());
  }

  /**
   * Gets total amount transacted per transaction type.
   * If the amounts add to Zero of not types match, it returns an object with 0 transaction amount
   *
   * @param type: String
   *
   * @return ResponseEntity<Map>
   */
  @GetMapping("/{type}/amount")
  public Object getTransactionAmountPerType(@PathVariable final String type) {
    Map<String, Object> map = new HashMap<>();
    AtomicReference<Double> total = new AtomicReference<>(0d);
    Object transactions = this.getTransactionsPerType(type);

    if (transactions instanceof ResponseEntity) {
      return transactions;
    }

    ((List<Transaction>)transactions)
      .forEach(tr -> total.updateAndGet(v -> v + tr.getTransactionAmount()));

    map.put("transactionType", type);
    map.put("totalAmount", total.get());

    return ResponseEntity.ok(map);
  }
}
