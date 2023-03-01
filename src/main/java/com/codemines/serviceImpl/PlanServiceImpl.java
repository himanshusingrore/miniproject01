package com.codemines.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemines.entites.Plan;
import com.codemines.entites.PlanCategory;
import com.codemines.repo.PlanCategoryRepo;
import com.codemines.repo.PlanRepo;
import com.codemines.services.PlanService;


@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private PlanCategoryRepo planCategoryRepo;
	
	
	@Override
	public Map<Integer, String> getPlanCategories() {
		// TODO Auto-generated method stub
		List<PlanCategory> planCategories= planCategoryRepo.findAll();
		Map<Integer,String> categoryMap=new HashMap<>();
		if(planCategories!=null)
		{
			for(PlanCategory planCategory:planCategories)
			{
				if(planCategory!=null)
				{
					categoryMap.put(planCategory.getCategoryId(), planCategory.getCategoryName());
				
				}
				
			}
			return categoryMap;
		}
		
		return null;
	}

	@Override
	public boolean savePlan(Plan plan) {
		// TODO Auto-generated method stub
		
		if(plan!=null)
		{
			Plan savedplan=planRepo.save(plan);
			if(savedplan.getPlanId()!=null)
				return true;
			else
				return false;
		}
		
		
		return false;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		// TODO Auto-generated method stub
		//apan check krlenge ki jis object ko update kr rhe hai uske paas planid hai ki bhi nhi..agr hai means already saved object 
		//ko apa update kr rhe hai or nahi hai means new plan hai ye..because agr pehle ye object save hua hoga toh kuch na kuch plan
		//id to generate hui hogi ..
		if(plan.getPlanId()!=null)
		{
		Plan update=planRepo.save(plan);//jpa k paas update k lie koi seprate method nahi hota ye save
		//method hi upsert ka kaam krta hai means it identtfy that ki agr is object k paas primarykey hai then it do update
		return true;
		}
		return false;
	}

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		List<Plan> allplanlist= planRepo.findAll();
		if(allplanlist!=null)
			return allplanlist;
		
		return null;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		// idhr findbyId optional type mai data/object return krta hai and ye optional ek type ka container hpta hai
		//jiske andr return huye object pde hai so get() ka use kr k us object ko nikaal k return krvaadegnge
		Optional<Plan> optionalcontainer= planRepo.findById(planId);
		if(optionalcontainer.isPresent())
			return optionalcontainer.get();
		
		return null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		// TODO Auto-generated method stub
		boolean status=false;
		try
		{
			planRepo.deleteById(planId);
			status=true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return status;
		}
		
		
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String activeSw) {
		// TODO Auto-generated method stub
		Optional<Plan> container = planRepo.findById(planId);
		if(container.isPresent())
		{
			Plan plan=container.get();
			plan.setActiveSw(activeSw);
			planRepo.save(plan);
			return true;
		}
		
		return false;
	}

}
