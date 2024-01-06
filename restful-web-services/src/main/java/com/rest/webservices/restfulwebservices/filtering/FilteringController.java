package com.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("value1","Value2","value3");
		//MappingJacksonValue for Dynamic filtering
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mappingJacksonValue.setFilters(filters); // mappingJacksonValue allowed to set msg 
		
		return mappingJacksonValue;
	}
	
	//static 
	
	@GetMapping("/filtering-list") //field1, field2
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value1","Value2","value3"),
							 new SomeBean("value4","Value5","value6"));
	}
	
	//dynamically
	@GetMapping("/dynamic-filtering-list") //field1, field2
	public MappingJacksonValue DynamicfilteringList() {
		
		List<SomeBean> list = Arrays.asList(new SomeBean("value1","Value2","value3"),
				 							new SomeBean("value4","Value5","value6"));
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mappingJacksonValue.setFilters(filters); // mappingJacksonValue allowed to set msg 
		
		return mappingJacksonValue;
	}
}
