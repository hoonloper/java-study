package kephispring.helloboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration // 구성 정보를 가지고 있는 클래스이다. 즉 팩토리 메소드인 것을 알려줌, 빈 팩토리 메소드를 가지는 것 이상으로 전체 애플리케이션을 구성하는 데 중요한 정보를 넣을 수 있어서 중요하다
@ComponentScan // 등록된 모든 Component 클래스들을 찾아서 빈으로 등록해 편해서 정극 추천. 편리해서 항상 좋은 것은 아니고, 정확하게 어떤 것들이 등록되는지 확인하려면 번거로울 수 있다.
public class HellobootApplication {
	/*
	// 팩토리 메소드 패턴 활용하기
	@Bean
	public HelloController helloController(HelloService helloService) {
		return new HelloController(helloService);
	}
	@Bean
	public HelloService helloService() {
		return new SimpleHelloService();
	}
	 */


	public static void main(String[] args) {

		// 스프링 컨테이너 통합하기
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh(); // 생략하면 안됨!

				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext ->
						servletContext.addServlet("dispatcherServlet",
								new DispatcherServlet(this)
						).addMapping("/*")
				);
				webServer.start();
			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();

		/*
		// Spring 컨테이너 만들기(Assembler)
		// 스프링 컨테이너는 오브젝트를 만들어 놓고 재사용하기에 싱글톤 패턴과 유사하다, 즉 싱글턴 레지스터라고 한다.
		// GenericApplicationContext applicationContext = new GenericApplicationContext(); DispatcherServlet 작업 전 프론트 컨트롤러 작업
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		applicationContext.registerBean(HelloController.class); // Bean 등록 끝
		applicationContext.registerBean(SimpleHelloService.class); // 컨트롤러 생성자에 주입한다(Dependency injection)
		applicationContext.refresh(); // 스프링 컨테이너 초기화 작업. Bean 오브젝트 생성해줌, efresh()는 이름이 좀 헷갈리는데 사실 start()임. 이제 컨테이너가 어떤 빈을 만들지 다 알게됐으니 빈을 모두 생성하고 연결하고 컨테이너로서 동작을 시작하게 하는 메소드

		// 아파치 톰캣 웹서버를 임의로 구현 - 서블릿 등록하기
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			// 대세 방식, Mapping 정보를 서블릿에 직접 넣는 대신 그 요청을 처리할 처리할 컨트롤러 클래스 안에다 매핑 정보를 직접 넣는 방식
			servletContext.addServlet("dispatcherServlet",
					new DispatcherServlet(applicationContext)
			).addMapping("/*");

			/* DispatcherServlet 전환 전 수동으로 컨트롤러 작업
			// 프론트 컨트롤러 처리
			servletContext.addServlet("frontcontroller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						// GET /hello
						// name을 가져와서 넣어주는 것, Dto라든지 Bean으로 넘겨주는 것을 바인딩
						String name = req.getParameter("name");

						// HelloController에 작업을 위임
						HelloController helloController = applicationContext.getBean(HelloController.class);
					    String ret = helloController.hello(name);

						// /hello로 요청된 응답을 여기에서 처리한다.
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
						// resp.setStatus(HttpStatus.OK.value()); GET은 생략 가능
						// resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); setContentType으로 간단히 설정 가능
					} else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*"); */
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
		});
		webServer.start();
		*/
	}
}
