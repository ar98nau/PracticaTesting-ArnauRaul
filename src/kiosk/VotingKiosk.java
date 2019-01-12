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
    private ElectoralOrganismImpl organism;
    private MailerServiceImpl mail;
    private Party actualVote;


    public VotingKiosk(){

    }

    public void setElectoralOrganism(ElectoralOrganism eO) { organism = (ElectoralOrganismImpl) eO; }
    public void setMailerService(MailerService mService){ mail = (MailerServiceImpl) mService; }

    public void vote(Party party) {
        organism.counter.scrutinize(party);
        actualVote = party;
    }

    public void sendeReceipt(MailAddress address) throws WrongInputException {
        DigitalSignature signature = organism.askForDigitalSignature(actualVote);
        mail.send(address, signature);
    }

    public ElectoralOrganismImpl getOrganism() {
        return organism;
    }

    public MailerServiceImpl getMail() {
        return mail;
    }

    public Party getActualVote() {
        return actualVote;
    }
}
