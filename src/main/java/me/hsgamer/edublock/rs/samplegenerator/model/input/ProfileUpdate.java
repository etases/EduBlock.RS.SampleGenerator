package me.hsgamer.edublock.rs.samplegenerator.model.input;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

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
            String fullName,
            boolean male,
            String avatar,
            Date birthDate,
            String address,
            String phone,
            String email
    ) {
        String[] names = fullName.split(" ");
        String firstName = names[names.length - 1];
        String lastName = fullName.substring(0, fullName.length() - firstName.length() - 1);

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
