package com.rarait.education.notification.email.spi;

import com.rarait.education.notification.email.impl.EmailRecord;
import com.rarait.framework.repository.BaseJpaRepository;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface EmailRepository extends BaseJpaRepository<EmailRecord,Integer> {

    EmailRecord findById(long emailId);
}