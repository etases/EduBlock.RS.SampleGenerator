package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RecordOutput {
    ClassroomOutput classroom = new ClassroomOutput();
    List<RecordEntryOutput> entries = Collections.emptyList();
    ClassificationReportOutput classification = new ClassificationReportOutput();
}
