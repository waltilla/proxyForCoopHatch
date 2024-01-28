package waltilla.sebastian.chickenCoopCloudBackend.ntfyWebClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebclientForHatchControl {

    private String urlToCoopHatch;
    private final WebClient webClient;
    private final String OPEN_HATCH_MESSAGE = "open_hatch";
    private final String CLOSE_HATCH_MESSAGE = "close_hatch";

    @Autowired
    public WebclientForHatchControl(@Value("${url.to.hatch}") String urlToCoopHatch) {
        this.urlToCoopHatch = urlToCoopHatch;
        this.webClient = WebClient.create();
    }

    public void sendOpenHatch() {
        webClient.post()
                .uri(urlToCoopHatch)
                .bodyValue(OPEN_HATCH_MESSAGE)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

    public void sendCloseHatch() {
        webClient.post()
                .uri(urlToCoopHatch)
                .bodyValue(CLOSE_HATCH_MESSAGE)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }
}
