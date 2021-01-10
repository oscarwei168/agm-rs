package tw.com.agm.rs.model;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ReportDtl
	implements java.io.Serializable {

    private static final long serialVersionUID = 4865969091138402471L;
    private String fieldId;
}
