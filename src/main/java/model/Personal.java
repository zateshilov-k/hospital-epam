package model;

import lombok.*;

@Data
@NoArgsConstructor
public class Personal {

    private long personalId;
    private String login; // it's like email
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

}