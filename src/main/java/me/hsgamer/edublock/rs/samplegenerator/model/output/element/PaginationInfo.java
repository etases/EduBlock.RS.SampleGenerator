package me.hsgamer.edublock.rs.samplegenerator.model.output.element;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PaginationInfo {
    int totalPages;
    int totalEntries;
    int pageNumber;
    int pageSize;
    boolean hasPreviousPage;
    boolean hasNextPage;

    public static PaginationInfo whole(Collection<?> collection) {
        return new PaginationInfo(1, collection.size(), 1, collection.size(), false, false);
    }
}
