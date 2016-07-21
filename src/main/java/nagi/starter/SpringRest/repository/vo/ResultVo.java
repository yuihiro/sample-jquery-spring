package nagi.starter.SpringRest.repository.vo;

public class ResultVo {

	public String status = "SUCCESS";
	public int update_cnt;
	public String message;
	public Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getUpdate_cnt() {
		return update_cnt;
	}

	public void setUpdate_cnt(int update_cnt) {
		this.update_cnt = update_cnt;
	}
}