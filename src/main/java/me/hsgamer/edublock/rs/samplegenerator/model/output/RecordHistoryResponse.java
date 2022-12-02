package me.hsgamer.edublock.rs.samplegenerator.model.output;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.RecordHistoryOutput;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RecordHistoryResponse {
    int status;
    String message;
    @Nullable
    List<RecordHistoryOutput> data;
}
