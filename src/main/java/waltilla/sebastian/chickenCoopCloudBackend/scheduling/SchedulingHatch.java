package waltilla.sebastian.chickenCoopCloudBackend.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import waltilla.sebastian.chickenCoopCloudBackend.ntfyWebClient.WebclientForHatchControl;

@Component
public class SchedulingHatch {

    WebclientForHatchControl controller;

    public SchedulingHatch(WebclientForHatchControl controller) {
        this.controller = controller;
    }

    @Scheduled(cron = "${schedule.morning}")
    public void openChickenCoopInTheMorning() {
        controller.sendOpenHatch();
    }

    @Scheduled(cron = "${schedule.evening}")
    public void closeChickenCoopInTheEvening() {
        controller.sendCloseHatch();
    }
}
