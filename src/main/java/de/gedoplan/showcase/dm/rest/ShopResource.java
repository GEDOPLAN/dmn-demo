package de.gedoplan.showcase.dm.rest;

import de.gedoplan.showcase.dm.model.Order;
import de.gedoplan.showcase.dm.service.ShopService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Random;

@Path("shop")
public class ShopResource {
  @Inject
  ShopService shopService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response checkOrder(Order order) {
    var numberOfOrders = getNumberOfOrders();
    order.setNumberOfOrders(numberOfOrders);
    order.setDiscount(shopService.getDiscount(numberOfOrders));
    return Response.ok(order).build();
  }

  private Integer getNumberOfOrders() {
    return new Random().nextInt(16);
  }
}
