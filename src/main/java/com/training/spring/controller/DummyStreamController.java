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
@CrossOrigin(origins = "http://localhost:8080")
public class DummyStreamController {
    private final AtomicBoolean inventoryRunning = new AtomicBoolean(false);
    private final List<String> epcList = List.of(
//            "32 30 32 35 2F 30 33 35 39 31",
//            "32 30 32 35 2F 30 33 35 39 32",
//            "32 30 32 35 2F 30 33 35 39 35",
//            "32 30 32 35 2F 30 33 36 30 34",
//            "32 30 32 35 2F 30 33 36 30 35",
//            "32 30 32 35 2F 30 33 36 30 36",
//            "32 30 32 35 2F 30 33 36 30 37",
//            "32 30 32 35 2F 30 33 36 31 30",
//            "32 30 32 35 2F 30 33 36 31 31",
//            "32 30 32 35 2F 30 33 36 31 32",
//            "32 30 32 35 2F 30 33 36 31 36",
//            "32 30 32 35 2F 30 33 36 31 37",
//            "32 30 32 35 2F 30 33 36 31 39",
//            "32 30 32 35 2F 30 33 36 32 34",
//            "32 30 32 35 2F 30 33 36 32 35"

//            "32 30 32 35 2F 30 33 35 37 37",
//            "32 30 32 35 2F 30 33 35 37 36",
//            "32 30 32 35 2F 30 33 35 37 32",
//            "32 30 32 35 2F 30 33 35 37 31",
//            "32 30 32 35 2F 30 33 35 37 30"

//            "42 4F 58 43 39 38 46 32 41 36 45 45",
//            "42 4F 58 32 32 36 36 41 35 45 34 36",
//            "42 4F 58 30 34 32 41 33 35 32 42 32",
//            "42 4F 58 45 41 45 38 43 38 41 45 44",
//            "42 4F 58 43 41 32 30 32 37 32 34 38",
//            "42 4F 58 45 32 39 42 33 46 37 42 36",
//            "42 4F 58 44 35 37 46 41 41 44 36 31",
//            "42 4F 58 30 34 33 39 33 32 36 37 35",
//            "42 4F 58 30 43 38 33 41 41 33 44 44",
//            "42 4F 58 41 33 41 31 36 37 39 30 36"

//            "42 4F 58 31 37 32 45 38 32 45 43 30",
//            "C3 A9 C3 B6 C3 BC",
//            "41 42 43 0D 0A 44 45 46",
//            "30 34 00 0A 9B 8C 12 34 00 00 00 01",
//            "41 42 00 43 44",
//            "41 02 43 44",
//            "C3 41 42",
//            "C3 A9 C3 B6 C3 BC",
//            "42 4F 58 41 32 44 41 31 33 35 44 41",
//            "42 4F 58 44 35 30 34 39 46 45 31 36",
//            "42 4F 58 41 41 43 30 32 31 36 36 42",
//            "42 4F 58 35 42 46 46 34 33 42 36 46",
//            "42 4F 58 42 41 42 39 33 36 33 41 35",
//            "42 4F 58 44 33 37 46 45 30 30 33 39",
//            "42 4F 58 37 32 36 35 30 43 44 46 30",
//            "42 4F 58 34 35 42 41 46 39 35 45 31",
//            "42 4F 58 41 35 42 35 36 30 46 37 36",
//            "42 4F 58 41 38 44 37 36 39 36 36 30",
//            "42 4F 58 31 37 32 45 38 32 45 43 30",
//            "42 4F 58 41 32 44 41 31 33 35 44 41",
//            "42 4F 58 44 35 30 34 39 46 45 31 36",
//            "42 4F 58 41 41 43 30 32 31 36 36 42",
//            "42 4F 58 35 42 46 46 34 33 42 36 46",
//            "42 4F 58 42 41 42 39 33 36 33 41 35",
//            "42 4F 58 44 33 37 46 45 30 30 33 39",
//            "42 4F 58 37 32 36 35 30 43 44 46 30",
//            "42 4F 58 34 35 42 41 46 39 35 45 31",
//            "42 4F 58 41 35 42 35 36 30 46 37 36",
//            "42 4F 58 41 38 44 37 36 39 36 36 30",
//            "42 4F 58 31 37 32 45 38 32 45 43 30",
//            "42 4F 58 41 32 44 41 31 33 35 44 41",
//            "42 4F 58 44 35 30 34 39 46 45 31 36",
//            "42 4F 58 41 41 43 30 32 31 36 36 42",
//            "42 4F 58 35 42 46 46 34 33 42 36 46",
//            "42 4F 58 42 41 42 39 33 36 33 41 35",
//            "42 4F 58 44 33 37 46 45 30 30 33 39",
//            "42 4F 58 37 32 36 35 30 43 44 46 30",
//            "42 4F 58 34 35 42 41 46 39 35 45 31",
//            "42 4F 58 41 35 42 35 36 30 46 37 36",
//            "42 4F 58 41 38 44 37 36 39 36 36 30"


            // 1
//            "42 4F 58 45 44 30 30 30 32 45 37 39",
//            "42 4F 58 43 37 38 32 43 36 37 33 34",
//            "42 4F 58 45 36 46 31 41 35 45 45 44"
            // 2
//            "42 4F 58 31 42 44 42 44 46 34 34 31",
//            "42 4F 58 41 44 39 44 35 35 44 32 34",
//            "42 4F 58 34 46 32 44 38 38 36 46 32"

            // Faktur
//            "32 30 32 35 2F 30 33 36 33 32"

            "42 4F 58 32 41 39 36 42 30 31 42 38",
            "42 4F 58 42 44 31 33 35 32 41 38 35",
            "42 4F 58 46 41 37 32 30 37 31 42 30",
            "42 4F 58 39 32 32 33 30 43 30 39 31",
            "42 4F 58 39 30 44 37 36 30 33 34 34",
            "42 4F 58 46 36 43 36 42 41 44 33 30",
            "DEVDEVDEVDEV",
            "HARUS DI MERGE",

            "32 30 32 35 2F 30 33 36 34 30",
            "32 30 32 35 2F 30 33 36 33 39",
            "32 30 32 35 2F 30 33 36 33 38",
            "32 30 32 35 2F 30 33 36 33 37"

    );

    @PostMapping("/commandAgent")
    public Map<String, Object> commandAgent(@RequestBody Map<String, String> request) {
        String command = request.getOrDefault("command", "").toUpperCase();
        boolean success = false;
        String message = "Invalid command";

        switch (command) {
            case "CONNECT":
                success = true;
                message = "Inventory connected successfully";
                System.out.println(message);
                break;
            case "START":
//                inventoryRunning.set(true);
                success = true;
                message = "Inventory started successfully";
                System.out.println(message);
                break;
            case "STOP", "DISCONNECT":
//                inventoryRunning.set(false);
                success = true;
                message = "Inventory stopped successfully";
                System.out.println(message);
                break;
        }

        return Map.of("success", success, "message", message);
    }

    @GetMapping(value = "/listTags/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamRfidTags() {
        List<Map<String, Object>> currentTags = new ArrayList<>();
        System.out.println("Stream invoked")          ;

        return Flux.interval(Duration.ofMillis(1000))
//                .takeUntil(tick -> !inventoryRunning.get())
//                .filter(tick -> inventoryRunning.get())
                .map(tick -> {
                    if (currentTags.size() < epcList.size()) {
                        int index = currentTags.size();
                        currentTags.add(Map.of(
                                "antId", 1,
                                "epc", epcList.get(index),
                                "readCount", 46 + index
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
                        return new ObjectMapper().writeValueAsString(response) + "\n\n";
                    } catch (JsonProcessingException e) {
                        return "{\"success\":false,\"message\":\"JSON error\"}\n\n";
                    }
                });
    }
}
