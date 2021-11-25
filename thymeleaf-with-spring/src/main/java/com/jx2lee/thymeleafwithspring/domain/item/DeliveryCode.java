package com.jx2lee.thymeleafwithspring.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * FAST: 빠른 배송
 * NORMAL: 일반 배송
 * SLOW: 느린 배송
 */

@Data
@AllArgsConstructor
public class DeliveryCode {
    private String code;
    private String displayName;
}
