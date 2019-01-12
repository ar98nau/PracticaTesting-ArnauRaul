package kiosk;


import Exception.WrongInputException;
import data.DigitalSignature;
import data.MailAddress;
import data.Party;
import services.ElectoralOrganism;
import services.MailerService;

/**
 * Implements a simplification of Use Case: Emit eVote
 */

public class VotingKiosk {
    private ElectoralOrganism organism;
    private MailerService mail;
    private Party actualVote;
    private VoteCounter counter;


    public VotingKiosk() {

    }

    public void setElectoralOrganism(ElectoralOrganism eO) { organism = eO; }
    public void setMailerService(MailerService mService){ mail = mService; }
    public void setVoteCounter(VoteCounter vCounter){ counter = vCounter; }

    public void vote(Party party) {
        counter.scrutinize(party);
        actualVote = party;
    }

    public void sendeReceipt(MailAddress address) throws WrongInputException {
        DigitalSignature signature = organism.askForDigitalSignature(actualVote);
        mail.send(address, signature);
    }

    public ElectoralOrganism getOrganism() {
        return organism;
    }

    public MailerService getMail() {
        return mail;
    }

    public VoteCounter getCounter() {
        return counter;
    }
//
//    public Party getActualVote() {
//        return actualVote;
//    }
}
