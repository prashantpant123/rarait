package com.rarait.education.notification.fcm;

import com.rarait.framework.domain.JobStatus;
import com.rarait.framework.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
public interface FcmRecordRepository extends BaseJpaRepository<FcmRecord, Long> {

    @Modifying
    @Query("UPDATE FcmRecord f SET f.status=?2, f.message=?3 WHERE f.id=?1 AND f.status=?4")
    void updateFcmRecord(long fcmRecordId, JobStatus newStatus, String message, JobStatus oldStatus);
}