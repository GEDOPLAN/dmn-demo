package de.gedoplan.showcase.dm.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.variable.Variables;

@ApplicationScoped
public class ShopService {
  private DmnEngine dmnEngine;
  private DmnDecision discountDecision;

  @PostConstruct
  void init() {
    dmnEngine = DmnEngineConfiguration
        .createDefaultDmnEngineConfiguration()
        .buildEngine();
    discountDecision = dmnEngine.parseDecision("discount", this.getClass().getResourceAsStream("/shop.dmn"));
  }

  public Integer getDiscount(Integer numberOfOrders) {
    var variableMap = Variables.createVariables()
        .putValue("orders", numberOfOrders);
    var decisionResult = dmnEngine.evaluateDecision(discountDecision, variableMap);
    return decisionResult.getSingleResult().getEntry("discount");
  }
}
