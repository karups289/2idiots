/**
 * 
 */
package com.eshiah.core;

/**
 * @author akaruppaiah
 *
 */
public class RuleRecord {
	private String ruleName;
	private String ruleTrigger;
	private String ruleAction;
	
	public RuleRecord(String ruleName,String ruleTrigger, String ruleAction){
		this.ruleName=ruleName;
		this.ruleAction=ruleAction;
		this.ruleTrigger=ruleTrigger;
	}

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * @return the ruleTrigger
	 */
	public String getRuleTrigger() {
		return ruleTrigger;
	}

	/**
	 * @param ruleTrigger the ruleTrigger to set
	 */
	public void setRuleTrigger(String ruleTrigger) {
		this.ruleTrigger = ruleTrigger;
	}

	/**
	 * @return the ruleAction
	 */
	public String getRuleAction() {
		return ruleAction;
	}

	/**
	 * @param ruleAction the ruleAction to set
	 */
	public void setRuleAction(String ruleAction) {
		this.ruleAction = ruleAction;
	}
	
}
