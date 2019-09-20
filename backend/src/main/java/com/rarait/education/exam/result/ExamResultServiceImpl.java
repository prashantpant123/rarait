package com.rarait.education.exam.result;

import com.rarait.education.exam.ExamResultRepository;
import com.rarait.education.exam.ExamResultService;
import com.rarait.education.exam.ExamResultSummaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@Service
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final ExamResultSummaryRepository examResultSummaryRepository;

    @Autowired
    public ExamResultServiceImpl(ExamResultRepository examResultRepository,
                                 ExamResultSummaryRepository examResultSummaryRepository) {
        this.examResultRepository = examResultRepository;
        this.examResultSummaryRepository = examResultSummaryRepository;
    }

//    @Override
//    public void addExamResult(){
//
//    }
}