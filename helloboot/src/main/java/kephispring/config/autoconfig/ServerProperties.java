package kephispring.config.autoconfig;


import kephispring.config.MyConfigurationProperties;
import org.springframework.stereotype.Component;

// BeanPostProcess 사용
@MyConfigurationProperties(prefix = "server")
public class ServerProperties {
    private String contextPath;
    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
