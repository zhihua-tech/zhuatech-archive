/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive.service;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class RecordDispositionGovernanceService {
    public Result evaluate(Request request) {
        List<String> blockers = new ArrayList<>();
        if (!request.retentionExpired()) blockers.add("保管期限尚未届满");
        if (request.legalHoldActive()) blockers.add("档案处于法律保全状态");
        if (request.auditFreezeActive()) blockers.add("档案处于审计冻结状态");
        if (!request.ownerApproved()) blockers.add("档案责任部门尚未审批");
        if (!request.evidencePackageComplete()) blockers.add("鉴定与销毁证据包不完整");
        if (!request.duplicateValidated()) blockers.add("未确认权威副本与重复件关系");
        String decision = request.legalHoldActive() || request.auditFreezeActive() ? "RETAIN"
                : blockers.isEmpty() ? "DISPOSE" : "REVIEW";
        return new Result(request.recordId(), decision, blockers.isEmpty(),
                List.copyOf(blockers), List.of("RETENTION", "LEGAL_HOLD", "OWNER_APPROVAL", "EVIDENCE"));
    }
    public record Request(@NotBlank String recordId, boolean retentionExpired,
                          boolean legalHoldActive, boolean auditFreezeActive,
                          boolean ownerApproved, boolean evidencePackageComplete,
                          boolean duplicateValidated) {
        public Request { if (recordId == null || recordId.isBlank()) throw new IllegalArgumentException("recordId is required"); }
    }
    public record Result(String recordId, String decision, boolean dispositionAllowed,
                         List<String> blockers, List<String> controlsChecked) {}
}
