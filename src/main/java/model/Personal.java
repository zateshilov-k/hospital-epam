package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Personal {

    private long personalId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

}
