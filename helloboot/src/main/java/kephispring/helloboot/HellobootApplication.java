package kephispring.helloboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	// 아파치 톰캣 웹서버를 임의로 구현 - 서블릿 등록하기
	public static void main(String[] args) {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			// 프론트 컨트롤러 처리
			servletContext.addServlet("frontcontroller", new HttpServlet() {
			 HelloController helloController = new HelloController();

				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						/* GET /hello */
						String name = req.getParameter("name");

						// HelloController에 작업을 위임
						// name을 가져와서 넣어주는 것, Dto라든지 Bean으로 넘겨주는 것을 바인딩
					    String ret = helloController.hello(name);

						// /hello로 요청된 응답을 여기에서 처리한다.
						resp.setStatus(HttpStatus.OK.value());
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					} else if (req.getRequestURI().equals("/user")) {

					} else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");
			/* 단일 서블렛 등록
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 동적인 응답을 만들어낸다.
					String name = req.getParameter("name");

					// /hello로 요청된 응답을 여기에서 처리한다.
					resp.setStatus(HttpStatus.OK.value());
					resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
					resp.getWriter().println("Hello " + name);
				}
			}).addMapping("/hello");
			 */
		});
		webServer.start();
	}
}
