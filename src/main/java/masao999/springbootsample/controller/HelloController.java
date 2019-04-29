package masao999.springbootsample.controller;

import masao999.springbootsample.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello APIのControllerクラス
 */
@RestController
public class HelloController {

    /**
     * hello APIのService
     */
    private final HelloService helloService;

    /**
     * コンストラクタ
     *
     * @param helloService hello APIのService
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * GETメソッドでのリクエストに対応
     *
     * @return レスポンス文字列
     */
    @GetMapping(path = "/hello")
    public String hello() {
        return helloService.hello();
    }
}
