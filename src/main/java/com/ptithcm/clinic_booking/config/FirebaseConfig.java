package com.ptithcm.clinic_booking.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Value("${firebase.credentials-path}")
    private String firebaseCredentialsPath;

    @Value("${firebase.storage.bucket-name}")
    private String firebaseStorageBucketName;
//          FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/firebase-key.json");
//          InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase/firebase-key.json");

    @PostConstruct
    public void initialize(){
        try{
            InputStream serviceAccount = getClass().getClassLoader()
                                                    .getResourceAsStream(firebaseCredentialsPath);
            if (serviceAccount == null) {
                throw new RuntimeException("Firebase service account key file not found in classpath");
            }
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(firebaseStorageBucketName)
                    .build();


            if(FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }

    @Bean
    public FirebaseApp firebaseApp() {
        return FirebaseApp.getInstance();
    }
}
