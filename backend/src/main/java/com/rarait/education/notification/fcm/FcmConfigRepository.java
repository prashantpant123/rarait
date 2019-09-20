package com.rarait.education.notification.fcm;

import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface FcmConfigRepository extends BaseJpaRepository<FcmConfig, Integer> {

    @Query("SELECT f.fcmKey FROM FcmConfig f WHERE f.institute.id = ?1")
    String findKeyByInstitute(int instituteId);
}
