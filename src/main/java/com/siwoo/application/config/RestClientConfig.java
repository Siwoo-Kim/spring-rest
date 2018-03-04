package com.siwoo.application.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClientConfig {

    @Bean
    public HttpComponentsClientHttpRequestFactory httpRequestFactory(){
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create().build();
        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(singerMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }


    @Bean
    public MarshallingHttpMessageConverter singerMessageConverter() {
        MarshallingHttpMessageConverter messageConverter = new MarshallingHttpMessageConverter();
        messageConverter.setMarshaller(castorMarshaller());
        messageConverter.setUnmarshaller(castorMarshaller());
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType xmlMeidaType = new MediaType("application","xml");
        mediaTypes.add(xmlMeidaType);
        messageConverter.setSupportedMediaTypes(mediaTypes);
        return messageConverter;
    }

    @Bean
    public CastorMarshaller castorMarshaller() {
        CastorMarshaller marshaller = new CastorMarshaller();
        marshaller.setMappingLocation(new ClassPathResource("META-INF/xml/oxm-mapping.xml"));
        return marshaller;
    }

}
