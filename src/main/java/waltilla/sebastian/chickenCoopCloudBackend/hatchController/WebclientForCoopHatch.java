package waltilla.sebastian.chickenCoopCloudBackend.hatchController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebclientForCoopHatch {

    private String urlToCoopHatch;
    private String lastthree;

    private final WebClient webClient;
    private final String OPEN_HATCH_MESSAGE = "open_hatch";
    private final String CLOSE_HATCH_MESSAGE = "close_hatch";

    @Autowired
    public WebclientForCoopHatch(@Value("${url.to.hatch}") String urlToCoopHatch,
    @Value("${url.to.lastthree}") String lastthree) {
        this.lastthree = lastthree;
        this.urlToCoopHatch = urlToCoopHatch;
        this.webClient = WebClient.create();
    }

    public void sendOpenHatch() {
        webClient.get()
                .uri(urlToCoopHatch + lastthree + "/" + OPEN_HATCH_MESSAGE)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

    public void sendCloseHatch() {
        webClient.get()
                .uri(urlToCoopHatch + lastthree + "/" + CLOSE_HATCH_MESSAGE)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }
}
