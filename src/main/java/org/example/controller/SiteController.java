package org.example.controller;

import org.example.domain.Access;
import org.example.domain.Site;
import org.example.domain.TypeAccess;
import org.example.repository.AccessRepo;
import org.example.repository.SiteRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SiteController {
    private final SiteRepo siteRepo;
    private final AccessRepo accessRepo;

    public SiteController(SiteRepo siteRepo, AccessRepo accessRepo) {
        this.siteRepo = siteRepo;
        this.accessRepo = accessRepo;
    }

    @GetMapping("/accesses")
    public String getSites(Model model) {
        model.addAttribute("sites", siteRepo.findAll());
        return "accesses";
    }

    @GetMapping("/accesses/{id}")
    public String getSites(@PathVariable("id") Site site,
                           Model model) {
        model.addAttribute("typeAccesses", TypeAccess.values());
        model.addAttribute("site", site);
        return "site";
    }

    @PostMapping("/accesses")
    public String AddSite(@RequestParam String nameSite,
                          Model model) {
        Site site = new Site();
        site.setNameSite(nameSite);
        siteRepo.save(site);
        return "redirect:/accesses";
    }

    @PostMapping("/accesses/{id}")
    public String addAccess(@PathVariable("id") Site site,
                            @RequestParam String url,
                            @RequestParam String login,
                            @RequestParam String password,
                            @RequestParam String typeAccess,
                            Model model) {
        Access access = new Access();
        access.setSite(site);
        access.setUrl(url);
        access.setLogin(login);
        access.setTypeAccess(typeAccess);
        access.setPassword(password);
        accessRepo.save(access);
        model.addAttribute("site", site);

        return "redirect:/accesses/{id}";
    }

    @PostMapping("/accessDel")
    public String delete(@ModelAttribute(value = "accessForDelete") Access access) {

        accessRepo.delete(access);
        return "redirect:/accesses";
    }

    @PostMapping("/siteDel")
    public String delete(@ModelAttribute(value = "siteForDelete") Site site) {

        siteRepo.delete(site);
        return "redirect:/accesses";
    }

}
