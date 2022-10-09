package com.javakc.cims.person.entity;

/**
 * @author zhouhonggang
 * @version 1.0.0
 * @project cims
 * @datetime 2022-03-19 12:04
 * @description: [人员对象属性]
 */
public class Person implements java.io.Serializable {
    /**
     * 主键
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证
     */
    private String code;
    /**
     * 单位
     */
    private String unit;
    /**
     * 状态 1:离休 2:退休
     */
    private int state;
    /**
     * 职级 1-6 省级正级-县级副职
     */
    private int grade;
    /**
     * 物业补贴 0:无 1:有
     */
    private int estate;
    /**
     * 供暖补贴 0:无 1:有
     */
    private int heating;
    /**
     * 更改原因
     */
    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getEstate() {
        return estate;
    }

    public void setEstate(int estate) {
        this.estate = estate;
    }

    public int getHeating() {
        return heating;
    }

    public void setHeating(int heating) {
        this.heating = heating;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", unit='" + unit + '\'' +
                ", state=" + state +
                ", grade=" + grade +
                ", estate=" + estate +
                ", heating=" + heating +
                ", reason='" + reason + '\'' +
                '}';
    }

}
