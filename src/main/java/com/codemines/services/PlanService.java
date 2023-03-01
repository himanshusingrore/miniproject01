package com.codemines.services;

import java.util.List;
import java.util.Map;

import com.codemines.entites.Plan;

public interface PlanService {
	
	//this is for get catgories
	public Map<Integer,String> getPlanCategories();
	
	//this is for save plan
	public boolean savePlan(Plan plan);
	
	//this is for update plan
		public boolean updatePlan(Plan plan);
	
	//get list of all plans
	public List<Plan> getAllPlans();
	
	//get specific plan for edit
	public Plan getPlanById(Integer planId);
	
	//to delete specific plan
	public boolean deletePlanById(Integer planId);
	
	//for softdelete means active /inactive feature
	//we will pass plan id and status for particular plan
	public boolean planStatusChange(Integer planId,String activeSw);
	
	

}
