package tw.com.agm.rs.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Sku
	implements Serializable {

    private static final long serialVersionUID = 2619838592243282156L;
    @ApiModelProperty(value = "SKU Id", example = "1001", required = true)
    private Long id;
}
