package me.hsgamer.edublock.rs.samplegenerator.list;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

@UtilityClass
public class FakerUtil {
    public Faker newFaker(Locale locale) {
        return new Faker(locale);
    }

    public Faker newFaker() {
        return newFaker(new Locale("vi"));
    }

    public <T> T createData(Predicate<Faker> predicate, Function<Faker, T> supplier) {
        Faker faker = newFaker();
        while (!predicate.test(faker)) {
            faker = newFaker();
        }
        return supplier.apply(faker);
    }

    public <T> T createData(Function<Faker, T> supplier) {
        return createData(faker -> true, supplier);
    }

    public <T> List<T> createDataList(int size, Predicate<Faker> predicate, Function<Faker, T> supplier) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(createData(predicate, supplier));
        }
        return list;
    }

    public <T> List<T> createDataList(int size, Function<Faker, T> supplier) {
        return createDataList(size, faker -> true, supplier);
    }
}
