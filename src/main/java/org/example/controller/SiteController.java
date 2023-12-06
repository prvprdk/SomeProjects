package org.example.controller;

import jakarta.validation.Valid;
import org.example.domain.Site;
import org.example.domain.TypeAccess;
import org.example.repository.SiteRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SiteController {
    private final SiteRepo siteRepo;

    public SiteController(SiteRepo siteRepo) {
        this.siteRepo = siteRepo;

    }

    @GetMapping("/accesses")
    public String getSites(Model model) {
        model.addAttribute("sites", siteRepo.findAll());
        return "accesses";
    }

    @GetMapping("/accesses/{id}")
    public String getSite(@PathVariable("id") Site site,
                           Model model) {
        model.addAttribute("typeAccesses", TypeAccess.values());
        model.addAttribute("site", site);

        return "site";
    }

    @PostMapping("/addSite")
    public String AddSite(@Valid Site site,
                          Model model) {
        siteRepo.save(site);

        return "redirect:/accesses";
    }

    @PostMapping("/siteDel")
    public String siteDel(@ModelAttribute(value = "siteForDelete") Site site) {

        siteRepo.delete(site);

        return "redirect:/accesses";
    }

}
