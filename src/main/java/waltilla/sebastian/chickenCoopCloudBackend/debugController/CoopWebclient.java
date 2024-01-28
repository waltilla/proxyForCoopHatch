package waltilla.sebastian.chickenCoopCloudBackend.debugController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import waltilla.sebastian.chickenCoopCloudBackend.hatchController.WebclientForHatchControl;

// TODO: remove when development is done.
@RestController
public class CoopWebclient {
    WebclientForHatchControl webClient;

    public CoopWebclient(WebclientForHatchControl webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/hatch/{openOrClose}")
    public void greetWithPathVariable(@PathVariable String openOrClose) {
        switch (openOrClose) {
            case "open_hatch" -> { webClient.sendOpenHatch();}
            case "close_hatch" -> { webClient.sendCloseHatch();}
        }
    }
}
