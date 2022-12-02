package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RecordHistoryOutput {
    Date timestamp = Date.from(Instant.EPOCH);
    List<RecordOutput> record = Collections.emptyList();
    String updatedBy = "";
}
