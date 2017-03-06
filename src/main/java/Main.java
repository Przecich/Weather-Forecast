

import java.time.LocalTime;
import java.util.Optional;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class Main {

    public static final Optional<String> port = Optional.ofNullable(System.getenv("PORT"));

    public static void main(String[] args) throws Exception {


        String contextPath = "/";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        String num ="12:00";

        tomcat.getConnector().setURIEncoding("UTF-8");
        tomcat.setPort(Integer.valueOf(port.orElse("8080") ));
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, appBase);
        tomcat.start();
        tomcat.getServer().await();
    }
}