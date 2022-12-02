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
public class RecordEntryOutput {
    long subjectId = 0;
    SubjectOutput subject = new SubjectOutput();
    float firstHalfScore = 0;
    float secondHalfScore = 0;
    float finalScore = 0;
    Date requestDate = Date.from(Instant.EPOCH);
    Date approvalDate = Date.from(Instant.EPOCH);
    boolean updateComplete = false;
    boolean fromFabric = false;
    AccountWithProfileOutput teacher = new AccountWithProfileOutput();
    AccountWithProfileOutput requester = new AccountWithProfileOutput();
    AccountWithProfileOutput approver = new AccountWithProfileOutput();
}
