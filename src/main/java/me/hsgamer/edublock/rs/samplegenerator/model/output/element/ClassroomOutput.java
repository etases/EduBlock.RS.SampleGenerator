package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ClassroomOutput {
    long id = -1;
    String name = "";
    int grade = -1;
    int year = -1;
    AccountWithProfileOutput homeroomTeacher = new AccountWithProfileOutput();
}