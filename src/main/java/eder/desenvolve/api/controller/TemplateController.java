package eder.desenvolve.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
    @Value("TEST-8009944509656865-070418-2909ea7590f102912b5ce2188d851370-68182493")
    private String mercadoPagoSamplePublicKey;

    @GetMapping
    public String renderMainPage(Model model) {
        model.addAttribute("publicKey", mercadoPagoSamplePublicKey);
        return "index";
    }
}