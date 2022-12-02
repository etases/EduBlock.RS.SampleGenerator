package me.hsgamer.edublock.rs.samplegenerator.model.input;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherWithSubjectListInput {
    List<TeacherWithSubjectInput> teachers;

    public boolean validate() {
        return teachers != null && teachers.stream().allMatch(TeacherWithSubjectInput::validate);
    }
}
