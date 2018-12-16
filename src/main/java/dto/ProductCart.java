package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductCart {
    Long id;
    String name;
    String description;
    Integer price;
    String photoUrl;
    Long itemId;
    
}