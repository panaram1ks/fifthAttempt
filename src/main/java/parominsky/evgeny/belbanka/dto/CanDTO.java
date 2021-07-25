package parominsky.evgeny.belbanka.dto;


import lombok.Data;
import parominsky.evgeny.belbanka.dto.enums.CanCode;

@Data
public class CanDTO {

    private CanCode canCode;
    private final int quantityPerSheet;

    private int firstOperationCounter;
    private int secondOperationCounter;
    private int thirdOperationCounter;

}
