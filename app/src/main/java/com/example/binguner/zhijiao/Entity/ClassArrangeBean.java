package com.example.binguner.zhijiao.Entity;

import java.util.List;

/**
 * Created by binguner on 2017/9/2.
 */

public class ClassArrangeBean {

    /**
     * status : success
     * info : [{"name":" 数据结构R","teacher":" 杨永强*","starAndEnd":"  1-16周上","weekDay":"  2","address":"  行知楼","classRoom":"  B115"},{"name":null,"teacher":null,"starAndEnd":"  1-16周上","weekDay":"  5","address":"  行知楼","classRoom":"  B115"},{"name":" 英语听力(三)","teacher":" 王茹*","starAndEnd":"  2,4,6,8,10,12,14,16周上","weekDay":"  4","address":"  行逸楼","classRoom":"  C-107"},{"name":" 物理实验B","teacher":" 杨玲珍*","starAndEnd":" ","weekDay":" ","address":" ","classRoom":" "},{"name":" 离散结构R","teacher":" 阎鹏飞*","starAndEnd":"  1-14周上","weekDay":"  5","address":"  行知楼","classRoom":"  B114"},{"name":null,"teacher":null,"starAndEnd":"  1-14周上","weekDay":"  1","address":"  行知楼","classRoom":"  A104"},{"name":" 形势与政策(三)","teacher":" 梁寅菁*","starAndEnd":"  1-3周上","weekDay":"  1","address":"  行远楼","classRoom":"  A107"},{"name":" 马克思主义基本原理","teacher":" 王江荔*","starAndEnd":"  1-10,12-17周上","weekDay":"  2","address":"  行知楼","classRoom":"  B117"},{"name":" 大学英语(三)","teacher":" 王茹*","starAndEnd":"  1-10,12-15周上","weekDay":"  1","address":"  行远楼","classRoom":"  B122"},{"name":null,"teacher":null,"starAndEnd":"  1-10,12-15周上","weekDay":"  4","address":"  行远楼","classRoom":"  B122"},{"name":" 概率论与数理统计E","teacher":" 白亮 王晓云*","starAndEnd":"  1-10,12-17周上","weekDay":"  3","address":"  行知楼","classRoom":"  C226"},{"name":" Java语言程序设计R","teacher":" 高保禄 孙静宇*","starAndEnd":"  1-10周上","weekDay":"  3","address":"  行远楼","classRoom":"  B121"},{"name":null,"teacher":null,"starAndEnd":"  1-10周上","weekDay":"  5","address":"  行知楼","classRoom":"  B115"},{"name":" 电路与电子技术R","teacher":" 张兴忠*","starAndEnd":"  1-12周上","weekDay":"  4","address":"  行远楼","classRoom":"  A102"},{"name":null,"teacher":null,"starAndEnd":"  1-12周上","weekDay":"  5","address":"  行知楼","classRoom":"  B117"}]
     */

    private String status;
    private List<InfoBean> info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * name :  数据结构R
         * teacher :  杨永强*
         * starAndEnd :   1-16周上
         * weekDay :   2
         * address :   行知楼
         * classRoom :   B115
         */

        private String name;
        private String teacher;
        private String starAndEnd;
        private String weekDay;
        private String address;
        private String classRoom;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getStarAndEnd() {
            return starAndEnd;
        }

        public void setStarAndEnd(String starAndEnd) {
            this.starAndEnd = starAndEnd;
        }

        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getClassRoom() {
            return classRoom;
        }

        public void setClassRoom(String classRoom) {
            this.classRoom = classRoom;
        }
    }
}
