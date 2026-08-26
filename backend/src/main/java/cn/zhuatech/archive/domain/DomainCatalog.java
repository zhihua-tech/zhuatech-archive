/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.archive.domain;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();

    public DomainCatalog() {
        actions.put("CHECK_IN", new WorkflowAction("CHECK_IN", "完成收集", List.of("待收集"), "待著录", "OPERATOR"));
        actions.put("ARCHIVE", new WorkflowAction("ARCHIVE", "确认归档", List.of("待著录"), "在库", "ADMIN"));
        actions.put("BORROW", new WorkflowAction("BORROW", "批准借阅", List.of("在库"), "借出", "ADMIN"));
        actions.put("RETURN", new WorkflowAction("RETURN", "归还入库", List.of("借出"), "在库", "OPERATOR"));
        actions.put("APPRAISE", new WorkflowAction("APPRAISE", "发起鉴定", List.of("在库"), "待处置", "ADMIN"));
        actions.put("DESTROY", new WorkflowAction("DESTROY", "批准销毁", List.of("待处置"), "已销毁", "ADMIN"));
    }

    public String systemName() { return "知华科技企业电子档案系统"; }
    public String scene() { return "档案收集、著录、归档、保管、借阅、鉴定、销毁与审计"; }
    public String initialStatus() { return "待收集"; }
    public String partyLabel() { return "档案/全宗"; }
    public String amountLabel() { return "档案价值"; }
    public String quantityLabel() { return "卷件数量"; }
    public String dueLabel() { return "保管期限"; }

    public List<ModuleDefinition> modules() {
        return List.of(
            new ModuleDefinition("ARCHIVE_PLAN", "归档计划", "制定部门归档范围、周期与责任清单"),
            new ModuleDefinition("COLLECTION", "档案收集", "接收电子文件并执行完整性校验"),
            new ModuleDefinition("CLASSIFICATION", "分类著录", "维护全宗、门类、保管期限与元数据"),
            new ModuleDefinition("TRANSFER", "移交归档", "形成移交批次、交接清单和签收记录"),
            new ModuleDefinition("STORAGE", "库房保管", "管理库位、载体、温湿度与数字保管"),
            new ModuleDefinition("BORROW", "借阅利用", "处理查阅、外借、到期归还和水印授权"),
            new ModuleDefinition("RETENTION", "保管期限", "计算到期时间并执行冻结和延期"),
            new ModuleDefinition("APPRAISAL", "档案鉴定", "组织价值鉴定、开放审核与销毁评审"),
            new ModuleDefinition("DESTRUCTION", "销毁管理", "双人复核销毁清单并留存不可抵赖证据"),
            new ModuleDefinition("AUDIT", "档案审计", "追踪下载、借阅、变更、移交和销毁行为")
        );
    }

    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }

    public record ModuleDefinition(String code, String name, String description) {}
    public record WorkflowAction(String code, String label, List<String> from, String to, String requiredRole) {}
}
