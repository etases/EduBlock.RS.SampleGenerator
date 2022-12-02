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
public class ClassificationOutput {
    String identifier = "";
    String name = "";
    List<String> otherNames = Collections.emptyList();
    int level = Integer.MAX_VALUE;
}
