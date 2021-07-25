package parominsky.evgeny.belbanka.dto;


import lombok.Data;
import parominsky.evgeny.belbanka.dto.enums.ProductCode;


@Data
public class ProductDTO {

    private ProductCode productCode;

    private CanDTO canDTO;

    private CoverDTO coverDTO;
}
