package com.demo.poc.entrypoint.shoppingcart.consultation.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity implements Serializable {

  private Long id;
  private String name;
  private String documentNumber;
  private String documentType;
}
