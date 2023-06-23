package org.example.controller;


import jakarta.annotation.Nullable;
import org.example.domain.Client;
import org.example.domain.Contract;
import org.example.domain.User;
import org.example.repository.ClientRepo;
import org.example.repository.UserRepo;
import org.example.service.ClientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class MainController {


    private final ClientRepo clientRepo;
    private final ClientService clientService;
    private final UserRepo userRepo;

    public MainController(ClientRepo clientRepo, ClientService clientService, UserRepo userRepo) {
        this.clientRepo = clientRepo;
        this.clientService = clientService;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/clients")
    public String greeting(@AuthenticationPrincipal User user, Model model) {

        long idAuthor = user.getId();
        model.addAttribute("contracts", Contract.values());
        model.addAttribute("clients", userRepo.findById(idAuthor).get().getClientList());
        return "clients";
    }

    @PostMapping("/clients")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      @RequestParam String company,
                      @RequestParam String email,
                      @RequestParam String phone,
                   //  @RequestParam List<String> contr,
                      @RequestParam String site
    ) {

        Client client = Client.builder()
                .name(name)
                .company(company)
                .email(email)
                .phone(phone)
                .site(site)
                .author(user)
                .build();



        clientService.add(client);
        return "redirect:/clients";
    }

    @PostMapping("/empDelete")
    public String delete(@ModelAttribute(value = "clientForDelete") Client client) {
      clientRepo.delete(client);

        return "redirect:/clients";
    }

    @GetMapping("clients/{id}")
    public String getClient(@PathVariable("id") Client client,
                            Model model) {

        long idAuthor = client.getAuthor().getId();
        model.addAttribute("client", client);
        model.addAttribute("contracts", Contract.values());
        model.addAttribute("clients", client.getAuthor().getClientList());
        return "clients";
    }

    @PostMapping("clients/{id}")
    public String update(@PathVariable("id") Client client,
                         @RequestParam String name,
                         @RequestParam String company,
                         @RequestParam String email,
                         @RequestParam String phone,
                         @RequestParam String site

                       //  @RequestParam @Nullable Set<Contract> form
    ) {

//        if (form != null) {
//            client.getContractSet().clear();
//            client.setContractSet(form);
//        }
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

        return "redirect:/clients";
    }

}
