package ua.khnu.util;

import java.util.List;
import java.util.Optional;

public final class Util {
    private Util() {
        throw new UnsupportedOperationException();
    }

    public static <T> Optional<T> getFirstOptionalFromList(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}
