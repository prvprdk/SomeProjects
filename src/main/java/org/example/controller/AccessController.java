package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.Access;
import org.example.domain.Site;
import org.example.repository.AccessRepo;
import org.example.service.CheckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AccessController {

    private final AccessRepo accessRepo;
    private final CheckService checkService;

    public AccessController(AccessRepo accessRepo, CheckService checkService) {
        this.accessRepo = accessRepo;

        this.checkService = checkService;
    }

    @PostMapping("/accesses/{id}")
    public String addAccess(@PathVariable("id") Site site,
                            @Valid Access access,
                            Model model) throws IOException {

        if (access.getTypeAccess() == null || access.getTypeAccess().isEmpty()) {
            return "redirect:/accesses/{id}";
        }

        access.setSite(site);

        access.setCheckAccess(checkService.check(access));
        accessRepo.save(access);

        model.addAttribute("site", site);

        return "redirect:/accesses/{id}";
    }

    @PostMapping("/accessDel")
    public String accessDel(@ModelAttribute(value = "accessForDelete") Access access, @RequestParam Long id_site) {
        accessRepo.delete(access);
        return "redirect:/accesses/" + id_site;
    }

}
