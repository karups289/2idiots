package com.eshiah.adapter;

import java.util.Date;

public class RulesVO {
	
	private int ruleId;
	private String ruleName;
	private String ruleCategory;
	private boolean interval;
	private Date dateFrom;
	private Date dateTo;
	private int period;
	private String direction;
	private String trigAction;
	
	
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleCategory() {
		return ruleCategory;
	}
	public void setRuleCategory(String ruleCategory) {
		this.ruleCategory = ruleCategory;
	}
	public boolean isInterval() {
		return interval;
	}
	public void setInterval(boolean interval) {
		this.interval = interval;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getTrigAction() {
		return trigAction;
	}
	public void setTrigAction(String trigAction) {
		this.trigAction = trigAction;
	}
	public RulesVO(int ruleId, String ruleName, String ruleCategory,
			boolean interval, Date dateFrom, Date dateTo, int period,
			String direction, String trigAction) {
		super();
		this.ruleId = ruleId;
		this.ruleName = ruleName;
		this.ruleCategory = ruleCategory;
		this.interval = interval;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.period = period;
		this.direction = direction;
		this.trigAction = trigAction;
	}
	public RulesVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
