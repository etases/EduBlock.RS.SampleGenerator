package me.hsgamer.edublock.rs.samplegenerator.model.input;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileUpdate {
    String firstName;
    String lastName;
    boolean male;
    String avatar;
    Date birthDate;
    String address;
    String phone;
    String email;

    public static ProfileUpdate of(
            String firstName,
            String lastName,
            boolean male,
            Date birthDate,
            String address,
            String phone,
            String email
    ) {
        String avatar = "https://avatars.dicebear.com/api/" + (male ? "male" : "female") + "/" + ThreadLocalRandom.current().nextInt(1000000, 9999999) + ".svg";
        return new ProfileUpdate(
                firstName,
                lastName,
                male,
                avatar,
                birthDate,
                address,
                phone,
                email
        );
    }

    public boolean validate() {
        boolean isValidName = firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank();
        boolean isValidDate = birthDate != null;
        boolean isValidPhone = phone != null && (phone.length() == 10 || phone.length() == 0);
        boolean isValidEmail = email != null && (email.contains("@") || getEmail().length() == 0);
        boolean isValidAvatar = avatar != null;
        boolean isValidAddress = address != null;

        return isValidName && isValidDate && isValidPhone && isValidEmail && isValidAvatar && isValidAddress;
    }
}
