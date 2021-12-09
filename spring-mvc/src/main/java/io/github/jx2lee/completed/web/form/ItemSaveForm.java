package io.github.jx2lee.completed.web.form;

import io.github.jx2lee.completed.domain.item.ItemType;
import io.github.jx2lee.completed.domain.item.SaveCheck;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ItemSaveForm {
    @NotBlank
    private String itemName;

    @NotNull()
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    private Boolean open; // 판매 여부
    private List<String> regions; // 등록 지역
    private ItemType itemType;
    private String deliveryCode;
}
