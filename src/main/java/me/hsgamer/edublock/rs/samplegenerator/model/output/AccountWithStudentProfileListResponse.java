package me.hsgamer.edublock.rs.samplegenerator.model.output;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.AccountWithStudentProfileOutput;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.PaginationInfo;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AccountWithStudentProfileListResponse {
    int status;
    String message;
    PaginationInfo pageInfo;
    @Nullable
    List<AccountWithStudentProfileOutput> data;
}
