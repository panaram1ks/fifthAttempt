package parominsky.evgeny.belbanka.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "metal")
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Metal extends PersistentEntity implements Serializable {
    private static final long serialVersionUID = 8L;

    @OneToMany(mappedBy = "metal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> productList;

    @NotNull
    @Size(min = 1, max = 10)
    private String name;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalTime;
    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private Integer amount;


}
