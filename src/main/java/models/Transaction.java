package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
  private String id;
  private String accountId;
  private String counterpartyAccount;
  private String counterpartyName;
  private String counterPartyLogoPath;
  private Double instructedAmount;
  private String instructedCurrency;
  private Double transactionAmount;
  private String transactionCurrency;
  private String transactionType;
  private String description;
}