/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive.controller;
import cn.zhuatech.archive.common.ApiResponse; import cn.zhuatech.archive.service.EnterpriseArchiveService; import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/archive") public class EnterpriseArchiveController {
 private final EnterpriseArchiveService service; /**
                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                  */
public EnterpriseArchiveController(EnterpriseArchiveService service){this.service=service;}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @PostMapping("/review-disposal") ApiResponse<?> execute(@Valid @RequestBody EnterpriseArchiveService.DisposalRequest request){return ApiResponse.ok(service.review(request));}
}

