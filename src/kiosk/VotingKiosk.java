package kiosk;


import data.*;
import services.ElectoralOrganism;
import services.ElectoralOrganismImpl;
import services.MailerService;
import services.MailerServiceImpl;
import Exception.WrongInputException;

/**
 * Implements a simplification of Use Case: Emit eVote
 */

public class VotingKiosk {
    private ElectoralOrganismImpl organisme;
    private MailerServiceImpl correu;
    private Party actualVote;


    public VotingKiosk(){
    }

    public void setElectoralOrganism(ElectoralOrganism eO) { organisme = (ElectoralOrganismImpl) eO; }
    public void setMailerService(MailerService mService){ correu = (MailerServiceImpl) mService; }

    public void vote(Party party) {
        organisme.counter.scrutinize(party);
        actualVote = party;
    }

    public void sendeReceipt(MailAddress address) throws WrongInputException {
        DigitalSignature signatura = organisme.askForDigitalSignature(actualVote);
        correu.send(address, signatura);
    }

    public ElectoralOrganismImpl getOrganisme() {
        return organisme;
    }

    public MailerServiceImpl getCorreu() {
        return correu;
    }

    public Party getActualVote() {
        return actualVote;
    }
}
