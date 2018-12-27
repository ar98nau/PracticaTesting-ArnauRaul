package kiosk;

import data.Party;
import services.ElectoralOrganism;
import services.MailerService;

/**
 * Implements a simplification of Use Case: Emit eVote
 */

public class VotingKiosk {
    ???

    public VotingKiosk() { ??? }

    public void setElectoralOrganism(ElectoralOrganism eO) { ??? }
    public void setMailerService(MailerService mService){ ???}

    public void vote(Party party) { ??? }

    public void sendeReceipt(MailAddress address) { ??? }
}
