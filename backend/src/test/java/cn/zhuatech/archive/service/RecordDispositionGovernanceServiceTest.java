/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class RecordDispositionGovernanceServiceTest {
    private final RecordDispositionGovernanceService service = new RecordDispositionGovernanceService();
    @Test void allowsDispositionWhenAllControlsPass() {
        var result = service.evaluate(new RecordDispositionGovernanceService.Request(
                "ARC-001", true, false, false, true, true, true));
        assertEquals("DISPOSE", result.decision());
        assertTrue(result.dispositionAllowed());
    }
    @Test void retainsRecordUnderLegalHold() {
        var result = service.evaluate(new RecordDispositionGovernanceService.Request(
                "ARC-002", true, true, false, true, true, true));
        assertEquals("RETAIN", result.decision());
        assertFalse(result.dispositionAllowed());
        assertTrue(result.blockers().contains("档案处于法律保全状态"));
    }
}
