package tw.com.agm.rs.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tw.com.agm.rs.dao.SampleRepository;
import tw.com.agm.rs.model.ReportDtl;

@Service
@CacheConfig(cacheNames = "sampleCache")
public class SampleService
	implements Serializable {

    private static final long serialVersionUID = 1377274998815032276L;
    @Autowired
    private SampleRepository sampleDao;

    @Cacheable
    public ReportDtl search(String aid, int counts) {
	return sampleDao.search(aid, counts);
    }
}
