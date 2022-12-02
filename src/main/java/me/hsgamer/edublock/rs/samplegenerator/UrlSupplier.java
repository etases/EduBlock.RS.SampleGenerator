package me.hsgamer.edublock.rs.samplegenerator;

import java.net.URI;
import java.util.Map;

public interface UrlSupplier {
    String get(String path);

    default URI getUri(String path) {
        return URI.create(get(path));
    }

    default URI getUri() {
        return getUri("");
    }

    default URI getUri(String path, String query) {
        return URI.create(get(path) + "?" + query);
    }

    default URI getUri(String path, Map<String, String> query) {
        return getUri(path, query.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).reduce((a, b) -> a + "&" + b).orElse(""));
    }
}