package kiosk;

import data.Party;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A class that represents the result in an election site
 */

public class VoteCounter {
    Set<Party> validParties;
    List<Integer> partiesVotes;
    int nullVotes;
    int blankVotes;
    int totalVotes;

    public VoteCounter(Set<Party> validParties) {
        this.validParties = validParties;
        nullVotes = 0;
        blankVotes = 0;
        totalVotes = 0;
        for(int i=0; i<validParties.size(); i++){
            partiesVotes.add(0);
        }
    }

    public void countParty(Party party) {
        int index = getPartyIndex(party);
        partiesVotes.set(index, partiesVotes.get(index)+1);
    }

    public void countNull() {
        nullVotes+=1;
    }
    public void countBlank() {
        blankVotes+=1;
    }
    public void scrutinize(Party party) {
        if(party.getName()=="blank"){
            countBlank();
        }else if(!validParties.contains(party)){
            countNull();
        }else{
            countParty(party);
        }
        totalVotes+=1;
    }

    public int getVotesFor(Party party) {
        int index = getPartyIndex(party);
        return partiesVotes.get(index);
    }

    public int getNulls() {
        return nullVotes;
    }
    public int getBlanks() {
        return blankVotes;
    }
    public int getTotal() {
        return totalVotes;
    }

    private int getPartyIndex(Party party){
        int contador = 0;
        Iterator iterador = validParties.iterator();
        while(iterador.hasNext()){
            Party actual = (Party) iterador.next();
            if(actual.equals(party)){
                return contador;
            }
            contador+=1;
        }
        return -1;
    }
}
