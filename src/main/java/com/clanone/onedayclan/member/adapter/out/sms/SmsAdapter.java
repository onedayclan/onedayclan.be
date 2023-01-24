package com.clanone.onedayclan.member.adapter.out.sms;

import com.clanone.onedayclan.member.application.port.out.SendSmsPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmsAdapter implements SendSmsPort {
    @Value("${navercloud.sms.host}")
    private String host;

    @Value("${navercloud.sms.service-id}")
    private String serviceId;

    @Value("${navercloud.sms.secret-key}")
    private String secretKey;

    @Value("${navercloud.sms.access-key}")
    private String accessKey;

    @Value("${navercloud.sms.service-url}")
    private String url;

    @Value("${navercloud.sms.from}")
    private String from;

    private final WebClient webClient;


    public String sendSMS(String content, String to) {
        SmsRequestBody body = SmsRequestBody.builder()
                .type("SMS")
                .from(from)
                .content(content)
                .messages(Collections.singletonList(SmsMessage.builder().to(to).build()))
                .build();
        try {
            String timestamp = Long.toString(System.currentTimeMillis());
            String signature = makeSignature(timestamp, "POST",url + serviceId + "/messages");

            Mono<String> monoString = webClient.post()
                    .uri(host + url + serviceId + "/messages")
                    .headers(headers -> {
                        headers.add("x-ncp-apigw-timestamp", timestamp);
                        headers.add("x-ncp-iam-access-key", accessKey);
                        headers.add("x-ncp-apigw-signature-v2", signature);
                    })
                    .body(Mono.just(body), SmsRequestBody.class)
                    .retrieve()
                    .bodyToMono(String.class);

            return monoString.block();

        } catch (UnsupportedEncodingException e) {
            log.error("지원하지 않는 인코딩 형식입니다. {}", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("알 수 없는 암호화 알고리즘입니다. {}", e);
        } catch (InvalidKeyException e) {
            log.error("유효하지 않은 key 입니다. {}", e);
        } catch (Exception e) {
            log.error("{}", e);
        }

        return null;
    }

    public String makeSignature(String timestamp, String method, String url) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";					// one space
        String newLine = "\n";					// new line

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }
}
