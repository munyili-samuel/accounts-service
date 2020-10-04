package com.bringglobal.services;

import com.bringglobal.utils.TestUtils;
import java.util.List;
import java.util.Optional;
import models.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RESTServiceTestCase {

  String url = "http://google.com";

  private Transaction transaction = new Transaction(
    "58aeed54-7042-456d-af86-f517bff5b7af",
    "savings-kids-john",
    "savings-kids-john",
    "ALIAS_03C57D",
    null,
    8.6,
    "GBP",
    8.6,
    "GBP",
    "SEPA",
    "This is a SEPA Transaction Request"
  );

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private RESTService restService = new RESTService();

  @Test
  public void shouldMapModelFromPayload() throws Exception {
    Mockito
      .when(restTemplate.getForObject(
        url, Object.class
      ))
      .thenReturn(TestUtils.getPayloadAsObject());

    Optional<List<Transaction>> transactions = restService.getTransactions(url);

    assertTrue(transactions.isPresent());
    assertEquals(transactions.get().get(0), transaction);
  }

}
