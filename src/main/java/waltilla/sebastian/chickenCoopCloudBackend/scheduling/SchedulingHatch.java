package waltilla.sebastian.chickenCoopCloudBackend.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import waltilla.sebastian.chickenCoopCloudBackend.hatchController.WebclientForHatchControl;

import java.time.LocalDateTime;

@Slf4j
@Component
public class SchedulingHatch {

    WebclientForHatchControl controller;

    public SchedulingHatch(WebclientForHatchControl controller) {
        this.controller = controller;
    }

    @Scheduled(cron = "${schedule.morning}")
    public void openChickenCoopInTheMorning() {
        log.info(LocalDateTime.now() +" Its morning, open!");
        controller.sendOpenHatch();
    }

    @Scheduled(cron = "${schedule.evening}")
    public void closeChickenCoopInTheEvening() {
        log.info(LocalDateTime.now() + " Its evening, close!" );
        controller.sendCloseHatch();
    }
}
