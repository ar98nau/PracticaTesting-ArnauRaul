package kiosk;

import Exception.WrongInputException;
import data.Party;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * A class that represents the result in an election site
 */

public class VoteCounter {
    private BigDecimal totalVotes = BigDecimal.ZERO;
    private HashMap<Party, BigDecimal> votes;
    private Party nullParty;
    private Party blankParty;

    public VoteCounter(Set<Party> validParties) throws WrongInputException {
        if(validParties==null) throw new WrongInputException();

        votes = new HashMap<>();
        Iterator iterator = validParties.iterator();
        while(iterator.hasNext()){
            votes.put((Party) iterator.next(), BigDecimal.ZERO);
        }
        nullParty = new Party("Null");
        blankParty = new Party("Blank");
        votes.put(nullParty, BigDecimal.ZERO);
        votes.put(blankParty, BigDecimal.ZERO);
    }

    public void countParty(Party party) {
        BigDecimal actual = votes.get(party);
        votes.put(party, actual.add(BigDecimal.ONE));
    }

    public void countNull() {
        BigDecimal actual = votes.get(nullParty);
        votes.put(nullParty, actual.add(BigDecimal.ONE));
    }
    public void countBlank() {
        BigDecimal actual = votes.get(blankParty);
        votes.put(blankParty, actual.add(BigDecimal.ONE));
    }

    public void scrutinize(Party party) {
        if(party.getName()=="Blank"){
            countBlank();
        }else if(party.getName()=="Null"){
            countNull();
        }else{
            countParty(party);
        }
        totalVotes = BigDecimal.ONE.add(totalVotes);
    }

    public int getVotesFor(Party party) {
        return votes.get(party).intValue();
    }

    public int getNulls() {
        return votes.get(nullParty).intValue();
    }
    public int getBlanks() {
        return votes.get(blankParty).intValue();
    }
    public int getTotal() {
        return totalVotes.intValue();
    }

    public BigDecimal getTotalVotes() {
        return totalVotes;
    }

    public Party getNullParty() {
        return nullParty;
    }

    public Party getBlankParty() {
        return blankParty;
    }
}
