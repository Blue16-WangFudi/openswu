package cn.blue16.swuLayer.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    private String studentId;   //学号,同样用于cookie或者sessionId等的用户标识
    private String name;        //姓名
    private String gender;      //性别
    private String college;     //学院
    private String major;       //专业
    private String ridNumber;   //身份证号
    private String className;   //班级名称
}
