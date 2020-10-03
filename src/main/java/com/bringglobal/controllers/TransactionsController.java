package com.bringglobal.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionsController {

  @GetMapping
  public ResponseEntity<Object> getSomething() {
    Map<String, Object> map = new HashMap<>();
    map.put("message", "This worked somewhat well");

    return ResponseEntity.ok(map);
  }
}
