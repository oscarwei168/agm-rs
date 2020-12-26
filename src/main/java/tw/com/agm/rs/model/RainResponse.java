package tw.com.agm.rs.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RainResponse
	implements Serializable {

    private static final long serialVersionUID = -7065206377167264653L;
    private String Success;
    private String Message;
    private String Payload;
}
