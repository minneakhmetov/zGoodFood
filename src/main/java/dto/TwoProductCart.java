package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TwoProductCart {
    Long firstId;
    String firstName;
    String firstDescription;
    Integer firstPrice;
    String firstPhotoUrl;
    Long firstItemId;
    Long secondId;
    String secondName;
    String secondDescription;
    Integer secondPrice;
    String secondPhotoUrl;
    Long secondItemId;
}