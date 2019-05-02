package masao999.springbootsample.controller;

import masao999.springbootsample.dto.ListAddRequestDto;
import masao999.springbootsample.entity.Directory;
import masao999.springbootsample.service.ListService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * list APIのControllerクラス
 */
@RestController
@Validated
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
     * @return directoryテーブルの全行
     */
    @GetMapping(path = "/list")
    public Map list() {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("response", listService.list().stream().map(
                Directory::getName).collect(Collectors.toList()));
        return responseMap;
    }

    /**
     * GETメソッドでのリクエストに対応
     *
     * @return directoryテーブルの指定されたIDに対応する行
     */
    @GetMapping(path = "/list/{id}")
    public Map listById(@PathVariable("id")
                        @NotNull
                        @Min(1)
                        @Max(9999)
                        @Valid final int id) {
        Map<String, Object> responseMap = new HashMap<>();
        listService.listById(id).ifPresent(
                directory -> responseMap.put("response", directory.getName()));
        return responseMap;
    }

    /**
     * POSTメソッドでのリクエストに対応
     *
     * @return OK応答
     */
    @PostMapping(path = "/list")
    @ResponseStatus(HttpStatus.CREATED)
    public Map listAdd(@RequestBody @Valid ListAddRequestDto dto) {
        Map<String, Object> responseMap = new HashMap<>();
        listService.listAdd(dto.getName());
        responseMap.put("response", "OK");
        return responseMap;
    }
}
