package parominsky.evgeny.belbanka.entity;



import lombok.Data;
import lombok.EqualsAndHashCode;
import parominsky.evgeny.belbanka.dto.enums.CoverCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity(name = "Cover")
@Table(name = "COVER")
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Cover extends PersistentEntity implements Serializable {

    private static final long serialVersionUID = 6L;

    @Enumerated(EnumType.STRING)
    private CoverCode coverCode;

    @Column
    private int quantityPerSheet;

    @Column
    @Min(value = 0)
    private int firstOperationCounter = 0;
    @Column
    @Min(value = 0)
    private int secondOperationCounter = 0;
    @Column
    @Min(value = 0)
    private int thirdOperationCounter = 0;
    @Column
    @Min(value = 0)
    private int fourthOperationCounter = 0;
}
