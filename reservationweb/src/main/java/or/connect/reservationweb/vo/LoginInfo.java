package or.connect.reservationweb.vo;

import java.time.LocalDateTime;

public class LoginInfo {
	private String email;
	private LocalDateTime createTime;
	
	public LoginInfo(String email) {
		this.email = email;
		createTime = LocalDateTime.now();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
}
