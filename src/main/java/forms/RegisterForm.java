package forms;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RegisterForm {
    String name;
    String surname;
    String email;
    String password;
}