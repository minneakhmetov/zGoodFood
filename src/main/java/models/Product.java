package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {
    Long id;
    String name;
    String description;
    Integer price;
    String photoUrl;

}