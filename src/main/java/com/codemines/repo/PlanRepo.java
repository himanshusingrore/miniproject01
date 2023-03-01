package com.codemines.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemines.entites.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{

}
