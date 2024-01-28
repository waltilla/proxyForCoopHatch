package waltilla.sebastian.chickenCoopCloudBackend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import waltilla.sebastian.chickenCoopCloudBackend.ntfyWebClient.WebclientForHatchControl;

@Component
public class Sceduler {

    WebclientForHatchControl controller;

    public Sceduler(WebclientForHatchControl controller) {
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
