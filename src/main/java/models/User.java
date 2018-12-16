package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class User {
    Long id;
    String name;
    String surname;
    String email;
    String hashPassword;
}