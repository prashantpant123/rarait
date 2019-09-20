package com.rarait.education.shared.setting;

import com.rarait.framework.setting.BaseDatasourceSetting;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties("datasource")
public class DatasourceSetting extends BaseDatasourceSetting {
}
