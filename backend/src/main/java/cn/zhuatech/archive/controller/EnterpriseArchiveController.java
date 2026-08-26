/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive.controller;
import cn.zhuatech.archive.common.ApiResponse; import cn.zhuatech.archive.service.EnterpriseArchiveService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/archive") public class EnterpriseArchiveController {
 private final EnterpriseArchiveService service; public EnterpriseArchiveController(EnterpriseArchiveService service){this.service=service;}
 @PostMapping("/review-disposal") ApiResponse<?> execute(@Valid @RequestBody EnterpriseArchiveService.DisposalRequest request){return ApiResponse.ok(service.review(request));}
}

