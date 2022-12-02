package me.hsgamer.edublock.rs.samplegenerator.list;

import lombok.experimental.UtilityClass;
import me.hsgamer.edublock.rs.samplegenerator.model.input.ProfileUpdate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class RandomInfo {
    public static String getRandomPhone() {
        return "097" + ThreadLocalRandom.current().nextInt(1000000, 9999999);
    }

    public static String getRandomAvatar(boolean male) {
        return "https://avatars.dicebear.com/api/" + (male ? "male" : "female") + "/" + ThreadLocalRandom.current().nextInt(1000000, 9999999) + ".svg";
    }

    public static Date getRandomDate(int year) {
        return Date.valueOf(LocalDate.of(year, ThreadLocalRandom.current().nextInt(1, 12), ThreadLocalRandom.current().nextInt(1, 28)));
    }

    public static ProfileUpdate getProfileUpdate(String fullName, boolean male, int year, String address, String email) {
        return ProfileUpdate.of(
                fullName,
                male,
                RandomInfo.getRandomAvatar(male),
                RandomInfo.getRandomDate(year),
                address,
                RandomInfo.getRandomPhone(),
                email
        );
    }
}
