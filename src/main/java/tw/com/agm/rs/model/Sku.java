package tw.com.agm.rs.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@ToString
@EqualsAndHashCode
public class Sku
	implements Serializable {

    private static final long serialVersionUID = 2619838592243282156L;
    @ApiModelProperty(value = "SKU Id", example = "1001", required = true)
    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
}
