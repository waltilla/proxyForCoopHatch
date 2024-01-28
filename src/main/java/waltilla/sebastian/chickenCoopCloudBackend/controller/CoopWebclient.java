package waltilla.sebastian.chickenCoopCloudBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import waltilla.sebastian.chickenCoopCloudBackend.ntfyWebClient.WebclientForHatchControl;

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
