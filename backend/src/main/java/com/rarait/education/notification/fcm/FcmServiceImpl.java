package com.rarait.education.notification.fcm;

import com.rarait.education.shared.Profiles;
import com.rarait.framework.domain.JobStatus;
import com.rarait.framework.shared.ProfileNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
@Profile(ProfileNames.PROD)
public class FcmServiceImpl implements FcmService {

    private final FcmRecordRepository fcmRepository;

    @Lazy
    @Autowired
    public FcmServiceImpl(FcmRecordRepository fcmRepository) {
        this.fcmRepository = fcmRepository;
    }

    @Override
    public void createFcm(FcmRequest request){
        FcmRecord record = new FcmRecord();
        record.setFcmId(request.getFcmId());
        record.setMessage(request.getMessage());
        record.setStatus(JobStatus.QUEUED);
        record.setAttempts(0);
        fcmRepository.save(record);
    }

    @Override
    @Transactional
    public void updateFcm(FcmUpdateRequest request){
        fcmRepository.updateFcmRecord(request.getId(),JobStatus.QUEUED, request.getMessage(),request.getStatus());
    }
}