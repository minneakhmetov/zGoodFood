package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Cart {
    Long id;
    Long userId;
    Long productId;
}