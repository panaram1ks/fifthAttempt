package parominsky.evgeny.belbanka.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import parominsky.evgeny.belbanka.dto.enums.ProductCode;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Product extends PersistentEntity implements Serializable {

    private static final long serialVersionUID = 7L;
    @Enumerated(EnumType.STRING)
    private ProductCode productCode;
    @OneToOne
    private Can can;
    @OneToOne
    private Cover cover;

    @ManyToOne(fetch = FetchType.LAZY)//optional = false значение не может быть null
    @JoinColumn(name = "metal_id")
    private Metal metal;
}
