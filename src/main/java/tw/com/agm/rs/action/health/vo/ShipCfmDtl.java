package tw.com.agm.rs.action.health.vo;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShipCfmDtl
	implements Serializable {

    private static final long serialVersionUID = -7490384216054015794L;
    private String sku;
    private String descr;
    private Integer qty;
    private String skugroup;
    private String barcode;
}
