package masao999.springbootsample.controller;

import masao999.springbootsample.entity.Sample;
import masao999.springbootsample.service.ListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * @return  sampleテーブルの全行
     */
    @GetMapping(path = "/hello")
    public List<Sample> list() {
        return listService.list();
    }
}
