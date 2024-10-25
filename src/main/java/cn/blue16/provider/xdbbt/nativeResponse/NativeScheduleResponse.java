package cn.blue16.provider.xdbbt.nativeResponse;

import cn.blue16.provider.xdbbt.nativeModel.CourseDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * NativeScheduleResponse 是一个表示原生调度响应的数据传输对象。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NativeScheduleResponse {

    /**
     * 响应状态码。
     */
    private int code;

    /**
     * 消息描述。
     */
    private String msg;

    /**
     * 调度响应结果列表。
     */
    private List<CourseDetail> res;


}