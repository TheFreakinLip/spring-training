package com.training.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("api/agent")
@CrossOrigin(origins = "http://localhost:5173")
public class DummyStreamController {
    private final AtomicBoolean inventoryRunning = new AtomicBoolean(false);
    private final List<String> epcList = List.of(
            "E2 80 69 15 00 00 40 12 32 C9 44 BC",
            "E2 80 69 15 00 00 40 12 32 C9 44 BD",
            "E2 80 69 15 00 00 40 12 32 C9 44 BE"
    );

    @PostMapping("/commandAgent")
    public Map<String, Object> commandAgent(@RequestBody Map<String, String> request) {
        String command = request.getOrDefault("command", "").toUpperCase();
        boolean success = false;
        String message = "Invalid command";

        switch (command) {
            case "START":
                inventoryRunning.set(true);
                success = true;
                message = "Inventory started successfully";
                break;
            case "STOP":
                inventoryRunning.set(false);
                success = true;
                message = "Inventory stopped successfully";
                break;
        }

        return Map.of("success", success, "message", message);
    }

    @GetMapping(value = "/listTags/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamRfidTags() {
        List<Map<String, Object>> currentTags = new ArrayList<>();

        return Flux.interval(Duration.ofSeconds(2))
                .takeUntil(tick -> !inventoryRunning.get())
                .filter(tick -> inventoryRunning.get())
                .map(tick -> {
                    if (currentTags.size() < epcList.size()) {
                        int index = currentTags.size();
                        currentTags.add(Map.of(
                                "antId", 1,
                                "epc", epcList.get(index),
                                "readCount", 49
                        ));
                    }
                    return currentTags;
                })
                .map(tags -> {
                    Map<String, Object> response = new LinkedHashMap<>();
                    response.put("success", true);
                    response.put("data", tags);
                    response.put("timestamp", ZonedDateTime.now(ZoneOffset.UTC).toString());
                    response.put("count", tags.size());
                    response.put("message", "Current tags list");

                    try {
                        return "data: " + new ObjectMapper().writeValueAsString(response) + "\n\n";
                    } catch (JsonProcessingException e) {
                        return "data: {\"success\":false,\"message\":\"JSON error\"}\n\n";
                    }
                });
    }
}
