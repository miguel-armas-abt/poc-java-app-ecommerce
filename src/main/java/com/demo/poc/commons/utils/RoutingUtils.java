package com.demo.poc.commons.utils;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutingUtils {

  public static String getQueryParam(String endpoint, String queryParamName) {
    return Optional.ofNullable(endpoint)
        .map(uri -> uri.split("\\?")[1])
        .map(queryParam -> queryParam.split("="))
        .filter(keyAndValue -> keyAndValue[0].equalsIgnoreCase(queryParamName))
        .map(keyAndValue -> keyAndValue[1])
        .orElseThrow(() -> new IllegalArgumentException("Invalid query param " + queryParamName));
  }

}
