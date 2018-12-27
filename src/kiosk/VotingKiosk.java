package kiosk;


import data.*;
import services.ElectoralOrganism;
import services.MailerService;
import Exception.WrongInputException;
import java.util.Set;

/**
 * Implements a simplification of Use Case: Emit eVote
 */

public class VotingKiosk {
    ElectoralOrganism organisme;
    MailerService mail;
    VoteCounter counter;

    public VotingKiosk(Set<Party> validParties) throws WrongInputException {
        counter = new VoteCounter(validParties);
    }

    public void setElectoralOrganism(ElectoralOrganism eO) { organisme = eO; }
    public void setMailerService(MailerService mService){ mail = mService; }

    public void vote(Party party) {
        counter.scrutinize(party);
    }

    public void sendeReceipt(MailAddress address) {

    }
}
