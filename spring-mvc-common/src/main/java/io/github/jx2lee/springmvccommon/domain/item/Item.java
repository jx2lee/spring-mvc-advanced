package io.github.jx2lee.springmvccommon.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
/**
 * @ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000")
 * 위와 같이 ScriptAssert 어노테이션을 이용해 Object Error, 즉 복합 에러를 처리할 수 있다.
 * 단, 제약사항이 많아 그리 많이 사용하지는 않은 편. 강사님은 Field Error 의 경우 아래와 같은 annotation 을 작성하고
   복합 필드 에러의 경우 java code 로 작성
**/
public class Item {

    @NotNull(groups = UpdateCheck.class)
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    private Boolean open; // 판매 여부
    private List<String> regions; // 등록 지역
    private ItemType itemType;
    private String deliveryCode;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
