package waltilla.sebastian.chickenCoopCloudBackend.NtfyWebSocket;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import waltilla.sebastian.chickenCoopCloudBackend.ntfyWebClient.WebclientForHatchControl;

@Service
public class HttpStreamService {

    private String topic;
    private WebClient webClient;

    private WebclientForHatchControl controller;

    public HttpStreamService(@Value("${ntfy.sh.baseurl}") String baseUrl,
                             @Value("${ntfy.sh.topic}") String topic,
                             WebclientForHatchControl controller) {
        this.topic = topic;
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
        this.controller = controller;
    }

    @PostConstruct
    public void run() {
        webClient.get()
                .uri(topic)
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(data -> {
                    System.out.println(data);
                    switch (data) {
                        case "open_hatch" -> {controller.sendOpenHatch();}
                        case "close_hatch" -> {controller.sendCloseHatch();}
                    }
                });
    }
}
