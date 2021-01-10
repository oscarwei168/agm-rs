package tw.com.agm.rs.action.health.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import tw.com.agm.rs.action.health.vo.ShipCfmDtl;
import tw.com.agm.rs.action.health.vo.ShipCfmHdr;

@Mapper
public interface HealthMapper {

    @Select("SELECT 1 FROM dual")
    int testSelect();

    @Select("SELECT * FROM AGM_INTERFACE.OUT_CHOICE_SHIPCFM_HDR WHERE PROCESS_STATUS = #{status}")
    @Results(id = "poResult", value = { @Result(property = "externpokey", column = "EXTERNPOKEY"),
	    @Result(property = "vendorno", column = "VENDORNO"), @Result(property = "vendorname", column = "VENDORNAME"),
	    @Result(property = "notes", column = "NOTES"), @Result(property = "status", column = "STATUS"),
	    @Result(property = "shipCfmDtl", column = "AID", javaType = List.class, many = @Many(select = "searchShipCfmDtl", fetchType = FetchType.EAGER)) })
    List<ShipCfmHdr> searchShipCfmHdr(String status);

    @Select("SELECT * FROM AGM_INTERFACE.OUT_CHOICE_SHIPCFM_DTL WHERE AID_OUT_CHOICE_SHIPCFM_HDR = #{aid}")
    @Results(id = "productResult", value = { @Result(property = "sku", column = "SKU"),
	    @Result(property = "descr", column = "DESCR"), @Result(property = "qty", column = "QTY", javaType = Integer.class),
	    @Result(property = "skugroup", column = "SKUGROUP"), @Result(property = "barcode", column = "BARCODE") })
    List<ShipCfmDtl> searchShipCfmDtl(String aid);
}
