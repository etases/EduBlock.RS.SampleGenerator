package me.hsgamer.edublock.rs.samplegenerator.model.output;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class StringResponse {
    int status;
    String message;
    @Nullable
    String data;
}
