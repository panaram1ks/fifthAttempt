package parominsky.evgeny.belbanka.dto;



import lombok.Data;
import parominsky.evgeny.belbanka.dto.enums.CoverCode;

@Data
public class CoverDTO {

    private CoverCode coverCode;
    private final int quantityPerSheet;

    private int firstOperationCounter;
    private int secondOperationCounter;
    private int thirdOperationCounter;
    private int fourthOperationCounter;
}
