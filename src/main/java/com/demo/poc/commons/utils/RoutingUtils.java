package com.demo.poc.commons.utils;

import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutingUtils {

  public static Map<String, String> getQueryParams(String endpoint) {
    String queryParams = endpoint.split("\\?")[1];
    String[] keyAndValue = queryParams.split("=");
    String key = keyAndValue[0];
    String value = keyAndValue[1];
    return Map.of(key, value);
  }

}
