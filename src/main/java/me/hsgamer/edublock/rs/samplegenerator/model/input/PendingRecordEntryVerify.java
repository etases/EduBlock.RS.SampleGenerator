package me.hsgamer.edublock.rs.samplegenerator.model.input;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PendingRecordEntryVerify {
    long id;
    boolean accepted;

    public boolean validate() {
        return id > 0;
    }
}
