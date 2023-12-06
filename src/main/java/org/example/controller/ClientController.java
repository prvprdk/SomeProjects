package org.example.controller;


import jakarta.validation.Valid;
import org.example.domain.Client;
import org.example.domain.Contract;
import org.example.domain.User;
import org.example.repository.UserRepo;
import org.example.service.ClientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ClientController {


    private final ClientService clientService;
    private final UserRepo userRepo;

    public ClientController(ClientService clientService, UserRepo userRepo) {
        this.clientService = clientService;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String index() {
        return "common";
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
                      @Valid Client client
    ) {
        client.setAuthor(user);
        clientService.save(client);
        return "redirect:/clients";
    }

    @PostMapping("/empDelete")
    public String delete(@ModelAttribute(value = "clientForDelete") Client client) {
        clientService.delete(client);
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
    public String update(@PathVariable("id") Client clientFromDb,
                         @Valid Client clientUpdate) {
        clientService.update(clientFromDb, clientUpdate);
        return "redirect:/clients";
    }
}
