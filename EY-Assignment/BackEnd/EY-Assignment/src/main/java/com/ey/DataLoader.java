package com.ey;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ey.model.Employee;
import com.ey.repository.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader implements ApplicationRunner {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		loadData();
	}


    @PostConstruct
    public void loadData() {
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 try {
             InputStream inputStream = getClass().getResourceAsStream("/Employee.json");
             List<Employee> employees = objectMapper.readValue(inputStream, new TypeReference<List<Employee>>() {});
             for(Employee employee : employees){
            	 employeeRepository.save(employee);
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

}
