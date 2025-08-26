package com.juanhelpes.qrcode.generator.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.juanhelpes.qrcode.generator.ports.StoragePort;

@Component
public class S3StorageAdapter implements StoragePort {

    private final String supabaseUrl;
    private final String apiKey;
    private final String bucket;
    private final RestTemplate restTemplate = new RestTemplate();

    public S3StorageAdapter(
            @Value("${supabase.url}") String supabaseUrl,
            @Value("${supabase.apiKey}") String apiKey,
            @Value("${supabase.bucket}") String bucket) {
        this.supabaseUrl = supabaseUrl;
        this.apiKey = apiKey;
        this.bucket = bucket;
    }

    public String uploadFile(String path, byte[] fileData, String contentType) {
        String url = supabaseUrl + "/storage/v1/object/" + bucket + "/" + path;

        System.out.println("Uploading file to: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("apikey", apiKey);

        HttpEntity<byte[]> entity = new HttpEntity<>(fileData, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + path;
        } else {
            throw new RuntimeException("Erro ao fazer upload: " + response.getBody());
        }
    }

}
