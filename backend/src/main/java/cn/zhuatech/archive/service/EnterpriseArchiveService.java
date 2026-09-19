/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive.service;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.time.*; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class EnterpriseArchiveService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public DisposalResult review(@Valid DisposalRequest r){
  List<String> blockers=new ArrayList<>(); if(r.retentionExpiry().isAfter(r.reviewDate())) blockers.add("尚未达到保管期限"); if(r.legalHold()) blockers.add("档案处于法务冻结"); if(r.pendingBorrow()) blockers.add("存在未归还借阅"); if(!r.appraisalApproved()) blockers.add("鉴定审批尚未通过"); if(!r.integrityVerified()) blockers.add("数字档案完整性校验未通过");
  return new DisposalResult(r.archiveNo(),r.reviewDate(),blockers,blockers.isEmpty(),"保密销毁",blockers.isEmpty()?"ELIGIBLE":"BLOCKED");
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record DisposalRequest(@NotBlank String archiveNo,@NotNull LocalDate retentionExpiry,@NotNull LocalDate reviewDate,boolean legalHold,boolean pendingBorrow,boolean appraisalApproved,boolean integrityVerified){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record DisposalResult(String archiveNo,LocalDate reviewDate,List<String> blockers,boolean destructionAllowed,String requiredMethod,String decision){}
}

