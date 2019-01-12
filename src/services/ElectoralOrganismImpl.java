package services;

import Exception.WrongInputException;
import data.DigitalSignature;
import data.Nif;
import data.Party;

import java.util.ArrayList;
import java.util.HashMap;


public class ElectoralOrganismImpl implements ElectoralOrganism {
    HashMap<Nif, Boolean> cens;  // suposarem que true significa que pot votar i false que no pot votar

    public ElectoralOrganismImpl (ArrayList<Nif> nifs) throws WrongInputException {
        if (nifs == null) throw new WrongInputException();

        cens = new HashMap<>();
        for(Nif nif : nifs){
            cens.put(nif, true);
        }
    }

    @Override
    public boolean canVote(Nif nif) {
        if (cens.containsKey(nif) && cens.get(nif) == true) return true;
        return false;
    }

    @Override
    public void disableVoter(Nif nif) {
        cens.put(nif, false);
    }

    @Override
    public DigitalSignature askForDigitalSignature(Party party) throws WrongInputException {
        return new DigitalSignature(Integer.toString(party.hashCode()));
    }
}
