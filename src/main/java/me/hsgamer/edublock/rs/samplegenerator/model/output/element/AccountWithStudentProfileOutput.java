package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AccountWithStudentProfileOutput {
    AccountOutput account = new AccountOutput();
    StudentOutput student = new StudentOutput();
    ProfileOutput profile = new ProfileOutput();
}
