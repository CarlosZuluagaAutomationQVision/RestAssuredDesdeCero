package Models.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegisterUser{

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

}
