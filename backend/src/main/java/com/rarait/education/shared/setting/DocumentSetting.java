package com.rarait.education.shared.setting;

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
@ConfigurationProperties("file")
public class DocumentSetting {

    private String logo;

    private String staffPicture;

    private String staffDocument;

    private String studentPicture;

    private String studentDocument;
}
