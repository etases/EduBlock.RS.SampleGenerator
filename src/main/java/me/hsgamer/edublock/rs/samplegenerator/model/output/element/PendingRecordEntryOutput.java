package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PendingRecordEntryOutput {
    long id;
    long subjectId = 0;
    SubjectOutput subject = new SubjectOutput();
    float firstHalfScore = 0;
    float secondHalfScore = 0;
    float finalScore = 0;
    Date requestDate = Date.from(Instant.EPOCH);
    AccountWithProfileOutput teacher = new AccountWithProfileOutput();
    AccountWithProfileOutput requester = new AccountWithProfileOutput();
    AccountWithStudentProfileOutput student = new AccountWithStudentProfileOutput();
    ClassroomOutput classroom = new ClassroomOutput();
}
