package com.codemines.restcontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codemines.appproperties.AppProperties;
import com.codemines.constants.MessagesConstants;
import com.codemines.entites.Plan;
import com.codemines.services.PlanService;

@RestController
public class PlanRestController {
	

	private PlanService planService;
	
	
	private AppProperties appProperties;
	
	private Map<String,String> message;
	
	//constructor injection krte hai the  @Autowired annotation lgane ki need nahi prti
		public PlanRestController(PlanService planService,AppProperties appProperties)
		{
			this.message=appProperties.getMessages();
			this.planService=planService;
		}
	
	//to get all category in response
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>> planCategories()
	{
		Map<Integer,String> categories=planService.getPlanCategories();
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	
	//saving plans 
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan)
	{
		String resMsg="";
		boolean isSaved = planService.savePlan(plan);
		if(isSaved)
		{
			resMsg=message.get(MessagesConstants.PLAN_SAVE);
		}
		else
		{
			resMsg=message.get(MessagesConstants.PLAN_FAIL);
		}
		return new ResponseEntity<>(resMsg,HttpStatus.CREATED);
		
	}
	
	//getting all plans in response
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> allPlans()
	{
		List<Plan> allPlans = planService.getAllPlans();
		
		return new ResponseEntity<>(allPlans,HttpStatus.OK);
	}
	
	//getting plan based in plan id which is used when user click on edit button
	@GetMapping("/plan/{planID}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planID)
	{
		Plan plan = planService.getPlanById(planID);
		
		return new ResponseEntity<>(plan,HttpStatus.OK);
	}
	
	//for delete plans based on id
	@DeleteMapping("/plans/{planID}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planID)
	{
		String resMsg="";
		boolean isdeleted = planService.deletePlanById(planID);
		if(isdeleted)
			resMsg=message.get(MessagesConstants.PLAN_DELETE);
		else
			resMsg=message.get(MessagesConstants.PLAN_DELETE_FAIL);
		
		return new ResponseEntity<>(resMsg,HttpStatus.OK);
	}
	
	//after click on edit button again subbmiting plan for update
	@PutMapping("/planupdate")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan)
	{
		String resMsg="";
		boolean updatePlan = planService.updatePlan(plan);
		if(updatePlan)
			resMsg=message.get(MessagesConstants.PLAN_UPDATE);
		else
			resMsg=message.get(MessagesConstants.PLAN_UPDATE_FAIL);
		
		return new ResponseEntity<>(resMsg,HttpStatus.OK);
	}
	
	//updating only status filed when user click on action
	@PutMapping("/statuschange/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status)
	{
		String resMsg="";
		boolean isstatuschanged = planService.planStatusChange(planId, status);
		if(isstatuschanged)
			resMsg=message.get(MessagesConstants.PLAN_STATUS_CHANGE);
		else
			resMsg=message.get(MessagesConstants.PLAN_STATUS_CHANGE_FAIL);
		
		return new ResponseEntity<>(resMsg,HttpStatus.OK);
	}
	

}//end
