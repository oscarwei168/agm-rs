package tw.com.agm.rs.model;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@ToString
@EqualsAndHashCode
public class ReportDtl
	implements java.io.Serializable {

    private static final long serialVersionUID = -2811484505471714826L;
    private String fieldId;

    public String getFieldId() {
	return fieldId;
    }

    public void setFieldId(String fieldId) {
	this.fieldId = fieldId;
    }
}
