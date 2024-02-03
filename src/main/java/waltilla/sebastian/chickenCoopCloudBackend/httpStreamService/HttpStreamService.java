package waltilla.sebastian.chickenCoopCloudBackend.httpStreamService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import waltilla.sebastian.chickenCoopCloudBackend.hatchController.WebclientForCoopHatch;

import java.time.LocalDateTime;

@Slf4j
@Service
public class HttpStreamService {

    private String topic;

    private WebClient webClient;

    private WebclientForCoopHatch controller;

    public HttpStreamService(@Value("${ntfy.sh.baseurl}") String baseUrl,
                             @Value("${ntfy.sh.topic}") String topic,
                             WebclientForCoopHatch controller) {
        this.topic = topic;
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
        this.controller = controller;
    }

    //https://docs.ntfy.sh/subscribe/api/#http-stream
    @PostConstruct
    public void run() {
        webClient.get()
                .uri(topic)
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(data -> {
                    if(!data.isBlank()) log.info(LocalDateTime.now() + " " + data);
                    switch (data) {
                        case "open_hatch" -> {controller.sendOpenHatch();}
                        case "close_hatch" -> {controller.sendCloseHatch();}
                    }
                });
    }
}
