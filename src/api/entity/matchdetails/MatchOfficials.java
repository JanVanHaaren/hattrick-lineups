package api.entity.matchdetails;

public class MatchOfficials {
	
	private Referee referee;
	
	private Referee refereeAssistant1;
	
	private Referee refereeAssistant2;

	public Referee getReferee() {
		return referee;
	}

	public void setReferee(Referee referee) {
		this.referee = referee;
	}

	public Referee getRefereeAssistant1() {
		return refereeAssistant1;
	}

	public void setRefereeAssistant1(Referee refereeAssistant1) {
		this.refereeAssistant1 = refereeAssistant1;
	}

	public Referee getRefereeAssistant2() {
		return refereeAssistant2;
	}

	public void setRefereeAssistant2(Referee refereeAssistant2) {
		this.refereeAssistant2 = refereeAssistant2;
	}
}
