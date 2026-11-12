package unil.rush.domain;

public enum Weather {
    CLEAR, CLOUDY, RAIN, SNOW, STORM;

    public static Weather fromCode(int code) {
        if (code == 0 || code == 1)
            return(CLEAR);
        if (code == 2 || code == 3 || code == 45 || code == 48)
            return(CLOUDY);
        if (code >= 51 && code <= 67 || (code >= 80 && code <= 82))
            return (RAIN);
        if (code == 71 || code == 77 || code == 73 || code == 75 || code == 85 || code == 86)
            return (SNOW);
        if (code >= 95 && code <= 99)
            return(STORM);
        else
            return(CLEAR);
    }


}