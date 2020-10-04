package com.bringglobal.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import models.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("unchecked")
@Service
public class RESTService {

  /**
   * Retrieves the transactions from the Open Bank Project
   * and maps the values to the <code><bold>Transactions Model</bold></code>
   * Throws Exception if the data structure received is unexpected
   * @return transactions
   */
  public Optional<List<Transaction>> getTransactions() throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    Object result = restTemplate.getForObject("https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions", Object.class);

    if (result instanceof Map) {
     try {
       List<LinkedHashMap<String, Object>> rawTransactions = (List<LinkedHashMap<String, Object>>) ((Map) result).get("transactions");

       List<Transaction> transactions = new ArrayList<>();

       rawTransactions.forEach(tr -> {
         Map<String, Object> otherAccount = (Map<String, Object>) tr.get("other_account");
         Map<String, Object> details = (Map<String, Object>) tr.get("detils");
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
       throw new Exception("Unexpected response structure received from Open Bank Project API");
     }
    }

    return Optional.empty();
  }
}
