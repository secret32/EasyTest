package org.mytest.springcloud.zuul;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author pc
 */
@Component
public class MyFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return new FallbackResponse(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return new FallbackResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    class FallbackResponse implements ClientHttpResponse {

        private HttpStatus status;

        public FallbackResponse(HttpStatus status) {
            this.status = status;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return status;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return status.value();
        }

        @Override
        public String getStatusText() throws IOException {
            return status.getReasonPhrase();
        }

        @Override
        public void close() {
        }

        @Override
        public InputStream getBody() throws IOException {
            JSONObject err = new JSONObject();
            err.put("code", "-1");
            err.put("msg", "服务不可用，请稍后再试");
            return new ByteArrayInputStream(err.toJSONString().getBytes());
        }

        @Override
        public HttpHeaders getHeaders() {
            HttpHeaders headers = new HttpHeaders();
            MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
            headers.setContentType(mt);
            return headers;
        }
    }
}
