package kiosk;

import data.Nif;
import data.Party;
import services.ElectoralOrganism;
import services.MailerService;

import java.util.Set;

/**
 * Implements a simplification of Use Case: Emit eVote
 */

public class VotingKiosk {
    ElectoralOrganism organisme;
    MailerService mail;
    VoteCounter counter;

    public VotingKiosk(Set<Party> validParties) {
        counter = new VoteCounter(validParties);
    }

    public void setElectoralOrganism(ElectoralOrganism eO) { organisme = eO; }
    public void setMailerService(MailerService mService){ mail = mService; }

    public void vote(Party party, Nif nif) {
        if(organisme.canVote(nif)){
            counter.scrutinize();
        }else{

        }

    }

    public void sendeReceipt(MailAddress address) { ??? }
}
