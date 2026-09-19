/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive.controller;
import cn.zhuatech.archive.common.ApiResponse;
import cn.zhuatech.archive.service.RecordDispositionGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/records")
public class RecordDispositionGovernanceController {
    private final RecordDispositionGovernanceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public RecordDispositionGovernanceController(RecordDispositionGovernanceService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/disposition-gate")
    public ApiResponse<RecordDispositionGovernanceService.Result> evaluate(
            @Valid @RequestBody RecordDispositionGovernanceService.Request request) { return ApiResponse.ok(service.evaluate(request)); }
}
