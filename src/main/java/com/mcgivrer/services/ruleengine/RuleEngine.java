package com.mcgivrer.services.rulengine;

public class RuleEngine{
  private ThreadPoolExecutor executors;
  private Map<String,RuleSet> rulesets = new HashMap<>();

  public RuleEngine(){
    executors = (threadPoolExecutor) Executors.create
  }
}
