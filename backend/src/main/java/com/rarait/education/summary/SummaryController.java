package com.rarait.education.summary;

import com.rarait.education.shared.route.InstituteRoute;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Prajin Maharjan
 * @since 1.0
 */
@RestController
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(InstituteRoute.SUMMARY)
    public SummaryResource getSummary() {
        return summaryService.getRecord();
    }
}
