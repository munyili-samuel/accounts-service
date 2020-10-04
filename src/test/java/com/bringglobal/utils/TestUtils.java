package com.bringglobal.utils;

import com.google.gson.Gson;
import java.util.LinkedHashMap;
import models.Transaction;

public class TestUtils {
  private static String getPayload(String dynamicPart) {
    return
      String.format("{"
        + "  \"transactions\": ["
        + "    {"
        + "      \"id\": \"58aeed54-7042-456d-af86-f517bff5b7af\","
        + "      \"this_account\": {"
        + "        \"id\": \"savings-kids-john\","
        + "        \"holders\": ["
        + "          {"
        + "            \"name\": \"Savings - Kids John\","
        + "            \"is_alias\": false"
        + "          }"
        + "        ],"
        + "        \"number\": \"832425-00304050\","
        + "        \"kind\": \"savings\","
        + "        \"IBAN\": null,"
        + "        \"swift_bic\": null,"
        + "        \"bank\": {"
        + "          \"national_identifier\": \"rbs\","
        + "          \"name\": \"The Royal Bank of Scotland\""
        + "        }"
        + "      },"
        + "      \"other_account\": {"
        + "        \"id\": \"5780MRN4uBIgWYmWAhI3pdqbSpItvOw4culXP5FWHJA\","
        + "        \"holder\": {"
        + "          \"name\": \"ALIAS_03C57D\","
        + "          \"is_alias\": true"
        + "        },"
        + "        \"number\": \"savings-kids-john\","
        + "        \"kind\": \"AC\","
        + "        \"IBAN\": \"4930396\","
        + "        \"swift_bic\": null,"
        + "        \"bank\": {"
        + "          \"national_identifier\": null,"
        + "          \"name\": \"rbs\""
        + "        },"
        + "        \"metadata\": {"
        + "          \"public_alias\": null,"
        + "          \"private_alias\": null,"
        + "          \"more_info\": null,"
        + "          \"URL\": null,"
        + "          \"image_URL\": null,"
        + "          \"open_corporates_URL\": null,"
        + "          \"corporate_location\": null,"
        + "          \"physical_location\": null"
        + "        }"
        + "      },"
        + "      \"%s\": {"
        + "        \"type\": \"SEPA\","
        + "        \"description\": \"This is a SEPA Transaction Request\","
        + "        \"posted\": \"2020-06-05T08:28:38Z\","
        + "        \"completed\": \"2020-06-05T08:28:38Z\","
        + "        \"new_balance\": {"
        + "          \"currency\": \"GBP\","
        + "          \"amount\": null"
        + "        },"
        + "        \"value\": {"
        + "          \"currency\": \"GBP\","
        + "          \"amount\": \"8.60\""
        + "        }"
        + "      },"
        + "      \"metadata\": {"
        + "        \"narrative\": null,"
        + "        \"comments\": [],"
        + "        \"tags\": [],"
        + "        \"images\": [],"
        + "        \"where\": null"
        + "      }"
        + "    }"
        + "  ]"
        + "}", dynamicPart);
  }

  public static final Transaction testTransaction = new Transaction(
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


  public static LinkedHashMap getPayloadAsObject(String dynamicPart) {
      return new Gson().fromJson(getPayload(dynamicPart), LinkedHashMap.class);
    }
}
