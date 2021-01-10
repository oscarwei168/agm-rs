package tw.com.agm.rs.action.health.vo;

import java.io.Serializable;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShipCfmHdr
	implements Serializable {

    private static final long serialVersionUID = 2020617363350260427L;
    private String externpokey;
    private String vendorno;
    private String vendorname;
    private String notes;
    private String status;
    private List<ShipCfmDtl> shipCfmDtl;
}
