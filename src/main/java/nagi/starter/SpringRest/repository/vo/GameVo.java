package nagi.starter.SpringRest.repository.vo;

public class GameVo {

	public int idx;

	public int priority = 1;
	public int player_1_idx;
	public int player_2_idx;
	public int result;
	public int extra;
	public String comment;

	public int player_1_score;
	public int player_2_score;

	public int player_1_team;
	public int player_2_team;

	public String player_1_id;
	public String player_2_id;

	public String player_1_team_name;
	public String player_2_team_name;

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getPlayer_1_idx() {
		return player_1_idx;
	}

	public void setPlayer_1_idx(int player_1_idx) {
		this.player_1_idx = player_1_idx;
	}

	public int getPlayer_2_idx() {
		return player_2_idx;
	}

	public void setPlayer_2_idx(int player_2_idx) {
		this.player_2_idx = player_2_idx;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getExtra() {
		return extra;
	}

	public void setExtra(int extra) {
		this.extra = extra;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getPlayer_1_score() {
		return player_1_score;
	}

	public void setPlayer_1_score(int player_1_score) {
		this.player_1_score = player_1_score;
	}

	public int getPlayer_2_score() {
		return player_2_score;
	}

	public void setPlayer_2_score(int player_2_score) {
		this.player_2_score = player_2_score;
	}

	public int getPlayer_1_team() {
		return player_1_team;
	}

	public void setPlayer_1_team(int player_1_team) {
		this.player_1_team = player_1_team;
	}

	public int getPlayer_2_team() {
		return player_2_team;
	}

	public void setPlayer_2_team(int player_2_team) {
		this.player_2_team = player_2_team;
	}

	public String getPlayer_1_id() {
		return player_1_id;
	}

	public void setPlayer_1_id(String player_1_id) {
		this.player_1_id = player_1_id;
	}

	public String getPlayer_2_id() {
		return player_2_id;
	}

	public void setPlayer_2_id(String player_2_id) {
		this.player_2_id = player_2_id;
	}

	@Override
	public String toString() {
		return "GameVo [priority=" + priority + ", player_1_idx=" + player_1_idx + ", player_2_idx=" + player_2_idx + ", result=" + result + ", extra=" + extra + ", comment="
				+ comment + ", player_1_score=" + player_1_score + ", player_2_score=" + player_2_score + ", player_1_team=" + player_1_team + ", player_2_team=" + player_2_team
				+ ", player_1_id=" + player_1_id + ", player_2_id=" + player_2_id + "]";
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getPlayer_1_team_name() {
		return player_1_team_name;
	}

	public void setPlayer_1_team_name(String player_1_team_name) {
		this.player_1_team_name = player_1_team_name;
	}

	public String getPlayer_2_team_name() {
		return player_2_team_name;
	}

	public void setPlayer_2_team_name(String player_2_team_name) {
		this.player_2_team_name = player_2_team_name;
	}

}
