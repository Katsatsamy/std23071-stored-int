package com.example.demo.endpoint.rest.controller.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@RestController
public class StoredIntController {
    private final Path FILE_PATH = Paths.get("data","stored-int.txt");

    @GetMapping("/stored-int")
    public ResponseEntity<Integer> getStoredInt() {
        try{
            Files.createDirectories(FILE_PATH.getParent());

            if(Files.exists(FILE_PATH)){
                String content = Files.readString(FILE_PATH);
                int storedInt = Integer.parseInt(content);
                return ResponseEntity.ok(storedInt);
            }else{
                int randomNumber = new Random().nextInt(1000000);

                Files.writeString(FILE_PATH, String.valueOf(randomNumber));
                return ResponseEntity.ok(randomNumber);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
