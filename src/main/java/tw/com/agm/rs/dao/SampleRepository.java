package tw.com.agm.rs.dao;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tw.com.agm.rs.model.ReportDtl;

@Repository
public class SampleRepository
	implements Serializable {

    private static final long serialVersionUID = 1272703908466854809L;
    private static final Logger logger = LoggerFactory.getLogger(SampleRepository.class);
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private ReportDtl dtl;

    public ReportDtl search(String aid, int counts) {
	RowCallbackHandler handler = (rs) -> dtl.setFieldId(rs.getString("FIELD_ID"));
	jdbc.query("SELECT * FROM (SELECT t.* FROM AGBS.MD_REPORT_FILE_DTL t WHERE AID = ?) WHERE ROWNUM <= ?",
		new Object[] { aid, counts }, handler);
	logger.info("dtl: {}", dtl);
	return dtl;
    }

    @SuppressWarnings("rawtypes")
    public List<ReportDtl> search(int count) {
	RowMapper<ReportDtl> mapper = (rs, i) -> {
	    dtl = new ReportDtl();
	    dtl.setFieldId(rs.getString("FIELD_ID"));
	    return dtl;
	};
	List list = jdbc.queryForList("SELECT * FROM(SELECT * FROM AGBS.MD_REPORT_FILE_DTL) WHERE ROWNUM <= ?",
		new Object[] { count }, mapper);
	logger.info("size: {}", list.size());
	return null;
    }
}
