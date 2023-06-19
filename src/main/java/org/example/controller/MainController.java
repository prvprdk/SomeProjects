package org.example.controller;


import jakarta.annotation.Nullable;
import org.example.domain.Client;
import org.example.domain.Contract;
import org.example.repository.ClientRepo;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Set;


@Controller
public class MainController {

    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ClientService clientService;


    @GetMapping("/clients")
    public String greeting(Model model) {
        model.addAttribute("contracts", Contract.values());
        model.addAttribute("clients", clientRepo.findAll());
        return "clients";
    }

    @PostMapping("/clients")
    public String add(@RequestParam String name,
                      @RequestParam String company,
                      @RequestParam String email,
                      @RequestParam String phone,
                      @RequestParam String site,
                      @RequestParam @Nullable Set<Contract> form,
                      Model model) {


        Client client = Client.builder()
                .name(name)
                .company(company)
                .email(email)
                .phone(phone)
                .site(site)
                .build();

        if (form != null) {
            client.setContractSet(form);
        }

        clientService.add(client);

        model.addAttribute("contracts", Contract.values());
        model.addAttribute("clients", clientRepo.findAll());
        return "clients";
    }

    @PostMapping("/empDelete")
    public String delete(@ModelAttribute(value = "clientForDelete") Client client, Model model) {
        clientRepo.delete(client);
        model.addAttribute("clients", clientRepo.findAll());
        return "redirect:clients";
    }

    @GetMapping("clients/{id}")
    public String getClient(@PathVariable("id") Client client, Model model) {


        model.addAttribute("client", client);
        model.addAttribute("contracts", Contract.values());
        model.addAttribute("clients", clientRepo.findAll());
        return "clients";
    }

    @PostMapping("clients/{id}")
    public String update(@PathVariable("id") Client client,
                         @RequestParam String name,
                         @RequestParam String company,
                         @RequestParam String email,
                         @RequestParam String phone,
                         @RequestParam String site,
                         @RequestParam @Nullable Set<Contract> form,

                         Model model) {
        if (form != null) {
            client.getContractSet().clear();
            client.setContractSet(form);
        }
        if (!StringUtils.isEmpty(name)) {
            client.setName(name);
        }
        if (!StringUtils.isEmpty(company)) {
            client.setCompany(company);
        }
        if (!StringUtils.isEmpty(email)) {
            client.setEmail(email);
        }
        if (!StringUtils.isEmpty(phone)) {
            client.setPhone(phone);
        }
        if (!StringUtils.isEmpty(site)) {
            client.setSite(site);
        }

        clientService.update(client);
        model.addAttribute("clients", clientRepo.findAll());
        return "redirect:/clients";
    }


    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

}
