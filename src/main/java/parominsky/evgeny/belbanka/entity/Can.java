package parominsky.evgeny.belbanka.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import parominsky.evgeny.belbanka.dto.enums.CanCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Can extends PersistentEntity implements Serializable {

    private static final long serialVersionUID = 5L;

    // @Column(name="Can_code", length=50, nullable=false, unique=false)
    @Enumerated(EnumType.STRING)
    private CanCode canCode;

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
}
