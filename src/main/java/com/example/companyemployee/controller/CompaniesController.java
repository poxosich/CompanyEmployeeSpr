package com.example.companyemployee.controller;

import com.example.companyemployee.model.Company;
import com.example.companyemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CompaniesController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public String companies(ModelMap modelMap) {
        List<Company> all = companyRepository.findAll();
        modelMap.addAttribute("companies", all);
        return "companies";
    }
    @GetMapping("/addCompanies")
    public String addCompanies(){
        return "addCompanies";
    }
    @PostMapping("/addCompanies")
    public String addCompaniesPost(@ModelAttribute Company company){

      companyRepository.save(company);
      return "redirect:/companies";
    }
    @GetMapping("/deleteCompany")
    public String deleteCompany(@RequestParam("id") int id) {
        companyRepository.deleteById(id);
        return "redirect:/companies";
    }
}
