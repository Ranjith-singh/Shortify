package com.url.shortener.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;
import com.url.shortener.models.User;
import com.url.shortener.models.UrlMapping;
import com.url.shortener.repository.UrlMappingRepository;
import com.url.shortener.dtos.UrlMappingDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UrlMappingService {
    private UrlMappingRepository urlMappingRepository;

    public UrlMappingDTO createShortUrl(String originalUrl, User user){
        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
        return convertToDto(savedUrlMapping);
            }
        
    public UrlMappingDTO convertToDto(UrlMapping urlMapping) {
        // TODO Auto-generated method stub
        UrlMappingDTO urlMappingDTO = new UrlMappingDTO();
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMappingDTO.setClickCount(urlMapping.getClickCount());
        urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());
        urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
        return urlMappingDTO;
    }

    public String generateShortUrl(){
        String charecters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder();

        for(int i=0;i<8;i++){
            shortUrl.append(charecters.charAt(random.nextInt(charecters.length())));
        }

        return shortUrl.toString();
    }
}
