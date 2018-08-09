package com.distributed.transaction.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-08-08-下午 1:21
 */
@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClient httpClient(HttpClientBuilder httpClientBuilder) {

        return httpClientBuilder.build();
    }

    @Bean(destroyMethod = "close")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        connectionManager.setMaxTotal(500);
        connectionManager.setDefaultMaxPerRoute(200);
        return connectionManager;
    }

    @Bean
    public HttpClientBuilder httpClientBuilder(PoolingHttpClientConnectionManager connectionManager) {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(6000).setSocketTimeout(6000)
                .setConnectionRequestTimeout(3000)
                .build();

        HttpClientBuilder builder= HttpClientBuilder.create();

        builder.setConnectionManager(connectionManager);

        builder.setDefaultRequestConfig(requestConfig);

//        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(0,false));
        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(1,true));


        return builder;
    }
}
