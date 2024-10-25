package cn.blue16.swuLayer.response;


import cn.blue16.provider.xdbbt.nativeModel.CourseDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResult {
    // TODO 根据前端需求对数据进行规范化，这里暂时使用的是Native类型返回
    private String studentId;
    private List<CourseDetail> res;
}
