package kiosk;


import data.*;
import services.ElectoralOrganism;
import services.ElectoralOrganismImpl;
import services.MailerService;
import Exception.WrongInputException;
import java.util.Set;

/**
 * Implements a simplification of Use Case: Emit eVote
 */

public class VotingKiosk {
    private ElectoralOrganismImpl organisme;
    private MailerService mail;

    public VotingKiosk(){
    }

    public void setElectoralOrganism(ElectoralOrganism eO) { organisme = (ElectoralOrganismImpl) eO; }
    public void setMailerService(MailerService mService){ mail = mService; }

    public void vote(Party party) {
        organisme.counter.scrutinize(party);
    }

    public void sendeReceipt(MailAddress address) {
    }
}
