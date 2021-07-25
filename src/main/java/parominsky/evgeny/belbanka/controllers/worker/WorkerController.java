package parominsky.evgeny.belbanka.controllers.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import parominsky.evgeny.belbanka.entity.Metal;
import parominsky.evgeny.belbanka.entity.Product;
import parominsky.evgeny.belbanka.service.CanService;
import parominsky.evgeny.belbanka.service.CoverService;
import parominsky.evgeny.belbanka.service.MetalService;
import parominsky.evgeny.belbanka.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    private MetalService metalService;
    private ProductService productService;
    private CanService canService;
    private CoverService coverService;

    @Autowired
    public WorkerController(MetalService metalService, ProductService productService, CanService canService, CoverService coverService) {
        this.metalService = metalService;
        this.productService = productService;
        this.canService = canService;
        this.coverService = coverService;
    }

    @GetMapping("/enterData")
    public String showData(Model model,
                           @RequestParam(value = "metalId", required = false) Integer metalId) {
        if (metalId != null) {
            Metal metal = metalService.getById(metalId);
            List<Product> productList = productService.getProductList(metal);
            System.out.println(productList.isEmpty());
            model.addAttribute("productList", productList);
        }
        model.addAttribute("metalList", metalService.getAllMetals());

        return "worker/formAddData";
    }


}
