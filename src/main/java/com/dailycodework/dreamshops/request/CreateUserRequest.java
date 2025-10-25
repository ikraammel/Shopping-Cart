package com.dailycodework.dreamshops.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
@Data
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    @NaturalId
    private String email;
    private String password;
}
