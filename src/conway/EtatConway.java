package conway;

import java.lang.reflect.Array;
import java.util.ArrayList;

public enum EtatConway {
    MORT("M"),
    VIVANT("V");

    private String name;
    EtatConway(String m) {
        this.name = m;
    }

    public static EtatConway getRandom() {
        return values()[0];
    }

    @Override
    public String toString() {
        return this.name;
    }
}
