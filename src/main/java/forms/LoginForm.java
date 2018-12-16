package forms;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginForm {
    String email;
    String password;
}