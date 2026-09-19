/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class RecordDispositionGovernanceServiceTest {
    private final RecordDispositionGovernanceService service = new RecordDispositionGovernanceService();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void allowsDispositionWhenAllControlsPass() {
        var result = service.evaluate(new RecordDispositionGovernanceService.Request(
                "ARC-001", true, false, false, true, true, true));
        assertEquals("DISPOSE", result.decision());
        assertTrue(result.dispositionAllowed());
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void retainsRecordUnderLegalHold() {
        var result = service.evaluate(new RecordDispositionGovernanceService.Request(
                "ARC-002", true, true, false, true, true, true));
        assertEquals("RETAIN", result.decision());
        assertFalse(result.dispositionAllowed());
        assertTrue(result.blockers().contains("档案处于法律保全状态"));
    }
}
