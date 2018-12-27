package kiosk;

/**
 * A class that represents the result in an election site
 */

public class VoteCounter {
    ???

    public VoteCounter(Set<Party> validParties) { ??? }

    public void countParty(Party party) { ??? }
    public void countNull() { ??? }
    public void countBlank() { ??? }
    public void scrutinize(Party party) { ??? }

    public int getVotesFor(Party party) { ??? }
    public int getNulls() { ??? }
    public int getBlanks() { ??? }
    public int getTotal() { ??? }
}
