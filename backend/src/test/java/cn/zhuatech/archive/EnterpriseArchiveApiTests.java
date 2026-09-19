/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive;
import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@SpringBootTest @AutoConfigureMockMvc class EnterpriseArchiveApiTests { @Autowired MockMvc mvc;

 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void eligibleArchiveCanEnterDestructionApproval() throws Exception {mvc.perform(post("/api/enterprise/archive/review-disposal").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"archiveNo":"ARC-001","retentionExpiry":"2025-12-31","reviewDate":"2026-08-26","legalHold":false,"pendingBorrow":false,"appraisalApproved":true,"integrityVerified":true}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.destructionAllowed").value(true)).andExpect(jsonPath("$.data.decision").value("ELIGIBLE"));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void legalHoldBlocksDestruction() throws Exception {mvc.perform(post("/api/enterprise/archive/review-disposal").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"archiveNo":"ARC-002","retentionExpiry":"2025-12-31","reviewDate":"2026-08-26","legalHold":true,"pendingBorrow":false,"appraisalApproved":true,"integrityVerified":true}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.destructionAllowed").value(false)).andExpect(jsonPath("$.data.blockers[0]").value("档案处于法务冻结"));}
}

