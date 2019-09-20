package com.rarait.education.exam.impl;

import com.rarait.education.exam.ExamRemarkRepository;
import com.rarait.education.exam.ExamRemarkService;
import com.rarait.education.exam.resource.ExamRemarkCreateRequest;
import com.rarait.education.shared.InstituteLoginSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class ExamRemarkServiceImpl implements ExamRemarkService {

    private final ExamRemarkRepository examRemarkRepository;
    private final InstituteLoginSession instituteLoginSession;

    public ExamRemarkServiceImpl(ExamRemarkRepository examRemarkRepository,
                                 InstituteLoginSession instituteLoginSession) {
        this.examRemarkRepository = examRemarkRepository;
        this.instituteLoginSession = instituteLoginSession;
    }

    @Override
    public void createRemark(ExamRemarkCreateRequest request) {
        ExamRemark remark = new ExamRemark();
        remark.setInstitute(instituteLoginSession.getCurrentInstitute());
        remark.setRemarks(request.getRemarks());
        examRemarkRepository.save(remark);
    }
}
