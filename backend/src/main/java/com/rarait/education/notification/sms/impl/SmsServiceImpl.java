package com.rarait.education.notification.sms.impl;

import com.rarait.education.notification.sms.SmsRecordRepository;
import com.rarait.education.notification.sms.SmsService;
import com.rarait.education.shared.Profiles;
import com.rarait.framework.domain.JobStatus;
import com.rarait.framework.shared.ProfileNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
@Profile(ProfileNames.PROD)
public class SmsServiceImpl implements SmsService {

    private final SmsRecordRepository smsRecordRepository;

    @Lazy
    @Autowired
    public SmsServiceImpl(SmsRecordRepository smsRecordRepository) {
        this.smsRecordRepository = smsRecordRepository;
    }

    @Override
    public void addSmsRecord(SmsRequest request){
        SmsRecord record = new SmsRecord();
        record.setMessage(request.getMessage());
        record.setMobileNumber(request.getMobileNumber());
        record.setAttempts(0);
        record.setQueuedTime(new Date());
        record.setStatus(JobStatus.QUEUED);
        record.setInstitudeId(request.getSenderUserId());
        smsRecordRepository.save(record);
    }
}
