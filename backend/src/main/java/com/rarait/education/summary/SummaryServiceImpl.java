package com.rarait.education.summary;

import com.rarait.education.institute.impl.Institute;
import com.rarait.education.shared.InstituteLoginSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;
    private final InstituteLoginSession instituteLoginSession;

    public SummaryServiceImpl(SummaryRepository summaryRepository,
                              InstituteLoginSession instituteLoginSession) {
        this.summaryRepository = summaryRepository;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    @Transactional
    public SummaryResource getRecord(){
        Summary summary = getSummary();
        return SummaryConvert.convert(summary);
    }

    @Async
    @Override
    @Transactional
    public void updateStudent() {
        Summary summary = getSummary();
        summary.setTotalStudent(summary.getTotalStudent() + 1);
        summaryRepository.save(summary);
    }

    @Async
    @Override
    @Transactional
    public void updateTeacher() {
        Summary summary = getSummary();
        summary.setTotalTeacher(summary.getTotalTeacher() + 1);
        summaryRepository.save(summary);
    }

    private Summary getSummary(){
        Institute institute = instituteLoginSession.getCurrentInstitute();
        Summary summary = summaryRepository.findOneByInstitute(institute.getId());
        if (summary == null) {
            summary = new Summary();
        }
        return summary;
    }
}
