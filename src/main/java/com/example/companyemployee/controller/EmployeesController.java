package com.example.companyemployee.controller;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;
import com.example.companyemployee.repository.CompanyRepository;
import com.example.companyemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employees")
    public String employees(ModelMap modelMap) {
        List<Employee> all = employeeRepository.findAll();
        modelMap.addAttribute("employees",all);
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addEmployeeP(ModelMap modelMap){
        List<Company> all = companyRepository.findAll();
        modelMap.addAttribute("companys",all);
        return "addEmployee";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute Employee employee){
        System.out.println(employee);
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}")
    public String siEmployee(@PathVariable("id") int id, ModelMap modelMap){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()){
            return "redirect:/";
        }
        modelMap.addAttribute("employee",employee.get());
       return "siEmployee";
    }
}
