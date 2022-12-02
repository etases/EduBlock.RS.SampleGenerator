package me.hsgamer.edublock.rs.samplegenerator.model.output;

import lombok.*;
import lombok.experimental.FieldDefaults;
import me.hsgamer.edublock.rs.samplegenerator.model.output.element.AccountWithProfileOutput;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AccountWithProfileResponse {
    int status;
    String message;
    @Nullable
    AccountWithProfileOutput data;
}
