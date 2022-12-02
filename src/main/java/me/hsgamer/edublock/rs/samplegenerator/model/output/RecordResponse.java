package me.hsgamer.edublock.rs.samplegenerator.model.output;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.RecordOutput;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RecordResponse {
    int status;
    String message;
    @Nullable
    RecordOutput data;
}
