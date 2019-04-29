package masao999.springbootsample.controller;

import masao999.springbootsample.service.ListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * list APIのControllerクラス
 */
@RestController
public class ListController {

    /**
     * list APIのService
     */
    private final ListService listService;

    /**
     * コンストラクタ
     *
     * @param listService list APIのService
     */
    public ListController(ListService listService) {
        this.listService = listService;
    }

    /**
     * GETメソッドでのリクエストに対応
     *
     * @return sampleテーブルの全行
     */
    @GetMapping(path = "/list")
    public Map list() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", listService.list().stream().map(
                sample -> sample.name).collect(Collectors.toList()));
        return responseMap;
    }
}
