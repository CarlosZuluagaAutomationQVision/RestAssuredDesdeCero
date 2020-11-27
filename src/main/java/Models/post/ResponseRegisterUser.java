package Models.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseRegisterUser{

	@JsonProperty("token")
	private String token;

	@JsonProperty("id")
	private int id;

}
