package com.codemines.appproperties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties(prefix="planapi")
@Configuration
public class AppProperties {
	//is class ka purpose hai ki jo constant message yml file mai diye ho usko access krna 
	//so @enableconfigurationproperti and @enableconfiguration jo hai directly yml se us 
	//particular prefix k andr wali property jisme key-valye pair mai valye hai usko uthaa ke
	//neche bnaaye huye map mai daaldega is map ka naam same rakhna as yml properti k jisme key-value store hai
	
	
	
	private Map<String,String> messages=new HashMap<>();

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}
	

}
