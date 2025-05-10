package model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String studentId;
    private String fullName;
    private String email;
    private String password;
    private LocalDate dob;
    private String gender;

}
