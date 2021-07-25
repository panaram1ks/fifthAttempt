package parominsky.evgeny.belbanka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import parominsky.evgeny.belbanka.entity.Metal;
import parominsky.evgeny.belbanka.service.MetalService;

@Controller
@RequestMapping("/metals")
public class MetalController {

    @Autowired
    private MetalService metalService;

    @GetMapping("/formMetal")
    public String formForMetal(Model model) {
        model.addAttribute("metal", new Metal());
        return "metal/createMetal";
    }


    @PostMapping("/createMetal")
    public String createMetal(Metal metal, Model model) {

        metalService.getRepository().save(metal);

        model.addAttribute("metal", new Metal());
        return "metal/createMetal";
    }
}
