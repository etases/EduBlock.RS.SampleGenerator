package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ClassificationReportOutput {
    ClassificationOutput firstHalfClassify = new ClassificationOutput();
    ClassificationOutput secondHalfClassify = new ClassificationOutput();
    ClassificationOutput finalClassify = new ClassificationOutput();
}
