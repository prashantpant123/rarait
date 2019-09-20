package com.rarait.education.notification.email.spi;

import com.rarait.education.notification.email.impl.EmailTemplate;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmailTemplateRepository extends BaseJpaRepository<EmailTemplate, Short> {

    @Query("SELECT e FROM EmailTemplate e WHERE e.name=?1")
    EmailTemplate findByTemplateName(String templateName);
}