package tw.com.agm.rs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import tw.com.agm.rs.model.Sku;

public interface SkuMapper {

    @Select("SELECT * FROM user")
    List<Sku> selectAll();
}
