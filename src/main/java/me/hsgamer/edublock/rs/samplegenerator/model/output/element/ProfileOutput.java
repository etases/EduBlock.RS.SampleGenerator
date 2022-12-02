package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ProfileOutput {
    long id = 0;
    String firstName = "";
    String lastName = "";
    boolean male = true;
    String avatar = "";
    Date birthDate = Date.from(Instant.EPOCH);
    String address = "";
    String phone = "";
    String email = "";
}
