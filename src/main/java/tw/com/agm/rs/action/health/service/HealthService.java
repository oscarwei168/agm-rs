package tw.com.agm.rs.action.health.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tw.com.agm.rs.action.health.dao.HealthMapper;
import tw.com.agm.rs.action.health.vo.ShipCfmHdr;

@Slf4j
@Service
@CacheConfig(cacheNames = "simpleCache")
public class HealthService
	implements Serializable {

    private static final long serialVersionUID = 1377274998815032276L;
    @Autowired
    private HealthMapper healthMapper;

    // @Cacheable
    public int test() {
	List<ShipCfmHdr> searchShipCfmHdr = healthMapper.searchShipCfmHdr("N");
	log.info("size: {}", searchShipCfmHdr.size());
	return healthMapper.testSelect();
    }
}
