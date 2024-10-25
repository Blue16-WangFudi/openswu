package cn.blue16.provider.xdbbt.nativeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Res 表示单个调度响应的结果信息。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class CourseDetail {

    /**
     * 层级名称。
     */
    private String bklxdjmc;

    /**
     * 教室ID。
     */
    private String cd_id;

    /**
     * 教室类型名称。
     */
    private String cdlbmc;

    /**
     * 教室名称。
     */
    private String cdmc;

    /**
     * 校区标识。
     */
    private String cxbj;

    /**
     * 校区标识名称。
     */
    private String cxbjmc;

    /**
     * 日期数字格式（如：2024年10月20日）。
     */
    private String dateDigit;

    /**
     * 日期分隔符格式（如：2024-10-20）。
     */
    private String dateDigitSeparator;

    /**
     * 日份（如：20）。
     */
    private String day;

    /**
     * 节数（如：1-2节）。
     */
    private String jc;

    /**
     * 节数简化版（如：1-2）。
     */
    private String jcor;

    /**
     * 节数简化版（同上）。
     */
    private String jcs;

    /**
     * 教工号ID。
     */
    private String jgh_id;

    /**
     * 教学评估标准。
     */
    private String jgpxzd;

    /**
     * 教学班是否开放标志。
     */
    private String jxbsftkbj;

    /**
     * 教学班状态。
     */
    private String jxbzc;

    /**
     * 课程标识（主修/辅修）。
     */
    private String kcbj;

    /**
     * 课程号。
     */
    private String kch;

    /**
     * 课程号ID。
     */
    private String kch_id;

    /**
     * 课程类型。
     */
    private String kclb;

    /**
     * 课程名称。
     */
    private String kcmc;

    /**
     * 课程学时总数（如：讲课:32, 实验:8）。
     */
    private String kcxszc;

    /**
     * 课程性质（如：通必）。
     */
    private String kcxz;

    /**
     * 课程总学时。
     */
    private String kczxs;

    /**
     * 开课方案名称。
     */
    private String khfsmc;

    /**
     * 开课状态。
     */
    private String kkzt;

    /**
     * 教学楼号。
     */
    private String lh;

    /**
     * 列表导航标志。
     */
    private boolean listnav;

    /**
     * 本地化语言键。
     */
    private String localeKey;

    /**
     * 月份（如：10）。
     */
    private String month;

    /**
     * 旧节次。
     */
    private String oldjc;

    /**
     * 旧周次。
     */
    private String oldzc;

    /**
     * 页面总数。
     */
    private int pageTotal;

    /**
     * 是否可分页。
     */
    private boolean pageable;

    /**
     * 排课班级标识。
     */
    private String pkbj;

    /**
     * 班级名称。
     */
    private String qqqh;

    /**
     * 排课顺序。
     */
    private String rk;

    /**
     * 人数定额。
     */
    private int rsdzjs;

    /**
     * 是否节假日上课。
     */
    private String sfjf;

    /**
     * 上课方式名称。
     */
    private String skfsmc;

    /**
     * 上课标识。
     */
    private String sxbj;

    /**
     * 学分。
     */
    private double xf;

    /**
     * 学期名称。
     */
    private String xm;

    /**
     * 学年。
     */
    private String xnm;

    /**
     * 星期代号。
     */
    private String xqdm;

    /**
     * 星期号（多个用逗号分隔）。
     */
    private String xqh1;

    /**
     * 星期号ID。
     */
    private String xqh_id;

    /**
     * 星期节次。
     */
    private String xqj;

    /**
     * 星期名称。
     */
    private String xqjmc;

    /**
     * 学期号。
     */
    private String xqm;

    /**
     * 校区名称。
     */
    private String xqmc;

    /**
     * 学校代码。
     */
    private String xsdm;

    /**
     * 学校类型标识。
     */
    private String xslxbj;

    /**
     * 年份。
     */
    private String year;

    /**
     * 周次范围（如：1-16周）。
     */
    private String zcd;

    /**
     * 教学班名称。
     */
    private String zcmc;

    /**
     * 主讲教师名称。
     */
    private String zfjmc;

    /**
     * 总学生数。
     */
    private String zhxs;

    /**
     * 总学时。
     */
    private String zxs;

    /**
     * 教学班性质扩展。
     */
    private String zxxx;

    /**
     * 教学班方向名称。
     */
    private String zyfxmc;

    /**
     * 教学班选课标志。
     */
    private String zyhxkcbj;

    /**
     * 教学班总人数。
     */
    private int zzrl;
}