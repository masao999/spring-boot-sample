package masao999.springbootsample.service.impl;

import masao999.springbootsample.repository.HelloRepository;
import masao999.springbootsample.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * hello APIのService実装クラス
 */
@Service
public class HelloServiceImpl implements HelloService {

    /**
     * hello APIのRepository
     */
    private final HelloRepository helloRepository;

    /**
     * コンストラクタ
     *
     * @param helloRepository hello APIのRepository
     */
    public HelloServiceImpl(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    /**
     * レスポンス文字列を返す
     *
     * @return レスポンス文字列
     */
    public String hello() {
        return helloRepository.hello();
    }
}
