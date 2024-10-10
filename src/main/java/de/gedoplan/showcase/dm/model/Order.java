package de.gedoplan.showcase.dm.model;

import lombok.Data;

@Data
public class Order {
  private Double amount;
  private Integer numberOfOrders;
  private Integer discount;
}
