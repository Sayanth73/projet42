package unil.rush.domain;

import java.util.UUID;

import static unil.rush.domain.RunStatus.IN_PROGRESS;

public class Run {
    private UUID runID;
    private UUID playerID;
    private int day;
    private int warnings;
    private int score;
    private RunStatus status;
    private DefeatCause defeatCause;

    public Run(UUID playerID) {
        setPlayerID(playerID);
        day = 1;
        warnings = 0;
        score = 0;
        status = IN_PROGRESS;
        defeatCause = null;

    }
    public Run()
    {

    }

    public int getScore()
    {
        return score;
    }
    public int getDay()
    {
        return day;
    }
    public int getWarnings()
    {
        return warnings;
    }
    public RunStatus getStatus()
    {
        return status;
    }
    public DefeatCause getDefeatCause()
    {
        return(defeatCause);
    }
    public UUID getRunID()
    {
        return (runID);
    }
    public UUID getPlayerID()
    {
        return(playerID);
    }
    public void setRunID(UUID runID)
    {
        this.runID = runID;
    }
    public void setDay(int day)
    {
        if (day < 1)
            throw new IllegalArgumentException("Day must start at 1");
        this.day = day;
    }
    public void setScore(int score)
    {
        if (score<0)
            throw new IllegalArgumentException("The score can not be a negative number");
        this.score = score;
    }
    public void setPlayerID(UUID playerID)
    {
        if (playerID == null)
            throw new IllegalArgumentException("The playerID can not be null");
        this.playerID=playerID;
    }

    public void addWarning()
    {
        if (status != RunStatus.IN_PROGRESS)
            throw new IllegalStateException("The run is finished");
        warnings = warnings + 1;
        if (warnings == 3) {
            defeatCause = DefeatCause.EXPELLED;
            status = RunStatus.LOST;
        }
    }




}
