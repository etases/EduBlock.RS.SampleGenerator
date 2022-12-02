package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class StudentOutput {
    long id = 0;
    String ethnic = "";
    String fatherName = "";
    String fatherJob = "";
    String motherName = "";
    String motherJob = "";
    String guardianName = "";
    String guardianJob = "";
    String homeTown = "";
}

