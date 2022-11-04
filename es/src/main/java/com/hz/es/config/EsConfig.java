package com.hz.es.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableElasticsearchRepositories
public class EsConfig {

    @Value("${es.cluster.name}")
    private String name;
    @Value("${es.user}")
    private String user;
    @Value("${es.password}")
    private String password;
    @Value("${es.url}")
    private String url;
    @Value("${es.port}")
    private int port;
    @Value("${es.http.ssl.keystore.password}")
    private String httpKeystorePassword;
    @Value("${es.http.ssl.truststore.password}")
    private String httpTruststorePassword;
    @Value("${es.http.ssl.enabled}")
    private String httpSslEnable;
    @Value("${es.transport.ssl.keystore.password}")
    private String keystorePassword;
    @Value("${es.transport.ssl.truststore.password}")
    private String truststorePassword;
    @Value("${es.transport.ssl.enabled}")
    private String transportSslEnabled;
    @Value("${es.transport.ssl.verification-mode}")
    private String transportVerificationMode;

    @Bean
    public TransportClient transportClient() {
        try {
            Resource resource = new ClassPathResource("elastic-certificates.p12");
            File file = resource.getFile();
            List<TransportAddress> list = getTransportAddress();
            TransportClient client = new PreBuiltXPackTransportClient(Settings.builder()
                    .put("cluster.name", name)
                    .put("xpack.security.user", user + ":" + password)
                    .put("xpack.security.transport.ssl.keystore.password", keystorePassword)
                    .put("xpack.security.transport.ssl.truststore.password", truststorePassword)
                    .put("xpack.security.transport.ssl.enabled", transportSslEnabled)
                    .put("xpack.security.transport.ssl.verification_mode", transportVerificationMode)
                    .put("xpack.security.http.ssl.keystore.password", httpKeystorePassword)
                    .put("xpack.security.http.ssl.truststore.password", httpTruststorePassword)
                    .put("xpack.security.http.ssl.enabled", httpSslEnable)
                    .put("xpack.security.transport.ssl.keystore.path", file.getAbsolutePath())
                    .put("xpack.security.transport.ssl.truststore.path", file.getAbsolutePath())
                    .put("xpack.security.http.ssl.keystore.path", file.getAbsolutePath())
                    .put("xpack.security.http.ssl.truststore.path", file.getAbsolutePath())
                    .build());
            for (TransportAddress node : list) {
                client.addTransportAddress(node);
            }
            return client;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(transportClient());
    }

    /**
     * 获取配置文件的es主机配置
     *
     * @return
     */
    public List<TransportAddress> getTransportAddress() {
        List<TransportAddress> list = new ArrayList<>();
        String[] addresses = url.split(",");
        for (String address : addresses) {
            TransportAddress node = new TransportAddress(new InetSocketAddress(address, port));
            list.add(node);
        }
        return list;
    }
}
