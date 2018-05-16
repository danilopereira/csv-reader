import lombok.Data;

import java.util.List;

@Data
public class CBOCreateUserRequest {
    private List<Long> clientId;
    private String name;
    private String email;
}
