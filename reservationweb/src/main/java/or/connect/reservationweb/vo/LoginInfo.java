package or.connect.reservationweb.vo;

import java.time.LocalDateTime;

public class LoginInfo {
	private String Email;
	private LocalDateTime createTime;
	
	public LoginInfo(String email) {
		this.Email = email;
		createTime = LocalDateTime.now();
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
}
