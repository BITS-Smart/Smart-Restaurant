package com.bitssmart.smartRestaurant.Controller;

import java.lang.Exception;
import io.sentry.Sentry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorMonitoringController {

    @RequestMapping(value={"/error_test"}, method = RequestMethod.GET)
    public void edit_menu() {
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
    }

}
