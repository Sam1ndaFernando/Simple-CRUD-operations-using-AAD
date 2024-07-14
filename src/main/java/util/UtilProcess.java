package util;

import java.util.UUID;

public class UtilProcess {
    public static String ganarateId() {
        return UUID.randomUUID().toString();
    }
}
