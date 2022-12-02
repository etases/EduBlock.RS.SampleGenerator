package me.hsgamer.edublock.rs.samplegenerator.model.output;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.PaginationInfo;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.PendingRecordEntryOutput;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PendingRecordEntryListResponse {
    int status;
    String message;
    PaginationInfo pageInfo;
    @Nullable
    List<PendingRecordEntryOutput> data;
}
