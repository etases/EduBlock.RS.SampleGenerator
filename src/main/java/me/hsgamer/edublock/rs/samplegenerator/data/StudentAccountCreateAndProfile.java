package me.hsgamer.edublock.rs.samplegenerator.data;

import me.hsgamer.edublock.rs.samplegenerator.model.input.ProfileUpdate;
import me.hsgamer.edublock.rs.samplegenerator.model.input.StudentUpdate;

public record StudentAccountCreateAndProfile(ProfileUpdate profileUpdate, StudentUpdate studentUpdate) {
    public static StudentAccountCreateAndProfile of(ProfileUpdate profileUpdate) {
        return new StudentAccountCreateAndProfile(profileUpdate, new StudentUpdate(
                "Kinh",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        ));
    }
}
