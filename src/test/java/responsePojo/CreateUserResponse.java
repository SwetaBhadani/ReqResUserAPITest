package responsePojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserResponse {
    String name;
    String job;
    String id;
    String createdAt;
}
