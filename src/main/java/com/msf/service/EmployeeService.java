package com.msf.service;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msf.EmployeeApplication;
import com.msf.model.Employee;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
//@RequestMapping(value = "/rest/student")
//@EnableEurekaClient
@EnableSwagger2
class EmployeeService {

	public static HashMap<Long,Employee> hmEmployee=new HashMap<Long,Employee>();
	private static int empId = 1;
	
	static {
		Employee one=new Employee("Bhaskar","Technology Specialist", 10000, empId++);
		hmEmployee.put(new Long(one.getId()),one);
		Employee two=new Employee("Vinay","Senior Architect", 20000, empId++);
		hmEmployee.put(new Long(two.getId()),two);
	}
	
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/employee/")
	public HashMap<Long, Employee> getEmployees() {		
		System.out.println(hmEmployee.toString());		
		return hmEmployee;
	}

//	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PostMapping("/employee/add")
	public Employee addEmployee(@RequestParam(value = "name") String name,
			@RequestParam(value = "designation", defaultValue = "unknown") String designation,
			@RequestParam(value = "salary", defaultValue = "1000") int salary) {

		Employee employee = new Employee(name, designation, salary, empId++);
		hmEmployee.put(new Long(employee.getId()), employee);
		return employee;

	}
	
	//@RequestMapping(value="/update",method = RequestMethod.PUT)
	@PutMapping("/employee/update")
	public Employee updateEmployee(@RequestBody Employee employee) throws Exception {
	
	 if(hmEmployee.containsKey(new Long(employee.getId()))){
		 hmEmployee.put(new Long(employee.getId()),employee);
	 }else{
	throw new Exception("Employee "+employee.getId()+" does not exists");
	 }
	
	 return employee;
	}
	
	 
	@Bean
	public Docket swaggerApi() {
	return new Docket(DocumentationType.SWAGGER_2)
	.select()
	.apis(RequestHandlerSelectors.basePackage("com.metlife.service"))
	.paths(PathSelectors.any())
	.build()
	.apiInfo(new ApiInfoBuilder().version("1.0").title("Employee API").description("Documentation Employee API v1.0").build());
	}
}