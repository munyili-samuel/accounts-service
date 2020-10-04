package com.bringglobal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("unchecked")
@Service
public class RESTService {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * Retrieves the transactions from the Open Bank Project
   * and maps the values to the <code><bold>Transactions Model</bold></code>
   * Throws Exception if the data structure received is unexpected
   * @return transactions
   */
  public Optional<List<Transaction>> getTransactions(final String url) throws Exception {
    Object result = restTemplate.getForObject(url, Object.class);

    if (result instanceof Map) {
     try {
       List<Map<String, Object>> rawTransactions = (List<Map<String, Object>>) ((Map) result).get("transactions");

       List<Transaction> transactions = new ArrayList<>();

       rawTransactions.forEach(tr -> {
         Map<String, Object> otherAccount = (Map<String, Object>) tr.get("other_account");
         Map<String, Object> details = (Map<String, Object>) tr.get("details");
         Map<String, String> value = (Map<String, String>) details.get("value");
         Double amount = Double.parseDouble(value.get("amount"));
         String currency = value.get("currency");

         Transaction transaction = new Transaction(
           (String) tr.get("id"),
           (String) ((Map<String, Object>)tr.get("this_account")).get("id"),
           (String) otherAccount.get("number"),
           (String) ((Map<String, Object>)otherAccount.get("holder")).get("name"),
           (String) ((Map<String, Object>)otherAccount.get("metadata")).get("image_URL"),
           amount,
           currency,
           amount,
           currency,
           (String) details.get("type"),
           (String) details.get("description")
         );

         transactions.add(transaction);
       });

       return Optional.of(transactions);
     } catch (Exception e) {
       e.printStackTrace();
       throw new Exception("Unexpected response structure received from Open Bank Project API");
     }
    }

    return Optional.empty();
  }
}
