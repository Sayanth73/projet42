package unil.rush.external;

public class OpenMeteoResponse {
    private Current current;
    public OpenMeteoResponse()
    {
    }
    public Current getCurrent()
    {
        return(current);
    }
    public void setCurrent(Current current1)
    {
        current = current1;
    }
}
