package parominsky.evgeny.belbanka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import parominsky.evgeny.belbanka.dto.enums.CanCode;
import parominsky.evgeny.belbanka.dto.enums.CoverCode;
import parominsky.evgeny.belbanka.dto.enums.ProductCode;
import parominsky.evgeny.belbanka.entity.Can;
import parominsky.evgeny.belbanka.entity.Cover;
import parominsky.evgeny.belbanka.entity.Product;
import parominsky.evgeny.belbanka.service.CanService;
import parominsky.evgeny.belbanka.service.CoverService;
import parominsky.evgeny.belbanka.service.MetalService;
import parominsky.evgeny.belbanka.service.ProductService;

import java.util.Arrays;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private CanService canService;
    private CoverService coverService;
    private MetalService metalService;

    @Autowired
    public ProductController(ProductService productService, CanService canService, CoverService coverService, MetalService metalService){
        this.productService = productService;
        this.canService = canService;
        this.coverService = coverService;
        this.metalService = metalService;
    }

    @GetMapping("/formProduct")
    public String formProduct(Model model){
        model.addAttribute("metalList", metalService.getAllMetals());
        model.addAttribute ("productCode", Arrays.asList(ProductCode.values()));
        return "product/createProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(ProductCode productCode,
                                @RequestParam("coverQuantity") Integer coverQuantity,
                                @RequestParam("canQuantity") Integer canQuantity) {
        Can can = new Can();
        can.setQuantityPerSheet(canQuantity);
        Cover cover = new Cover();
        cover.setQuantityPerSheet(coverQuantity);
        Product product = new Product();
//        switch (productCode) {
//            case  PRODUCT_500:
//                product.setProductCode(ProductCode.PRODUCT_500);
//                can.setCanCode(CanCode.CAN_500);
//                cover.setCoverCode(CoverCode.COVER_500);
//                break;
//            case PRODUCT_250:
//                product.setProductCode(ProductCode.PRODUCT_250);
//                can.setCanCode(CanCode.CAN_250);
//                cover.setCoverCode(CoverCode.COVER_250);
//                break;
//            case PRODUCT_125:
//                product.setProductCode(ProductCode.PRODUCT_125);
//                can.setCanCode(CanCode.CAN_125);
//                cover.setCoverCode(CoverCode.COVER_125);
//                break;
//            case PRODUCT_50:
//                product.setProductCode(ProductCode.PRODUCT_50);
//                can.setCanCode(CanCode.CAN_50);
//                cover.setCoverCode(CoverCode.COVER_50);
//                break;
//            case PRODUCT_30:
//                product.setProductCode(ProductCode.PRODUCT_30);
//                can.setCanCode(CanCode.CAN_30);
//                cover.setCoverCode(CoverCode.COVER_30);
//                break;
//            case PRODUCT_10:
//                product.setProductCode(ProductCode.PRODUCT_10);
//                can.setCanCode(CanCode.CAN_10);
//                cover.setCoverCode(CoverCode.COVER_10);
//                break;
//            default:
//                break;
//        }
        canService.getRepository().save(can);
        coverService.getRepository().save(cover);
        product.setCan(can);
        product.setCover(cover);
        productService.getRepository().save(product);
        return "redirect:formProduct";
    }

}
