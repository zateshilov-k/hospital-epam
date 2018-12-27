package model;

import lombok.*;

@Data
@NoArgsConstructor
public class Personal {

    private long personalId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    protected boolean canEqual(Object other) {
        return other instanceof Personal;
    }




}
