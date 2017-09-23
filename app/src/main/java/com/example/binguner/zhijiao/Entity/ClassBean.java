package com.example.binguner.zhijiao.Entity;

import java.util.List;

/**
 * Created by binguner on 2017/9/1.
 */

public class ClassBean {

    /**
     * table : [{"time":"第一小节(08:00-08:50)","wednesday":"   ","monday":"  大学英语(三)_15(明向校区行远楼B122)","friday":"  Java语言程序设计R_08(明向校区行知楼B115)","sunday":"   ","thursday":"  电路与电子技术R_08(明向校区行远楼A102)","saturday":"   ","tuesday":"   "},{"time":"第二小节(09:00-09:50)","wednesday":"   ","monday":"  大学英语(三)_15(明向校区行远楼B122)","friday":"  Java语言程序设计R_08(明向校区行知楼B115)","sunday":"   ","thursday":"  电路与电子技术R_08(明向校区行远楼A102)","saturday":"   ","tuesday":"   "},{"time":"第三小节(10:10-11:00)","wednesday":"  Java语言程序设计R_08(明向校区行远楼B121)","monday":"   ","friday":"  电路与电子技术R_08(明向校区行知楼B117)","sunday":"   ","thursday":"   ","saturday":"   ","tuesday":"  马克思主义基本原理_12(明向校区行知楼B117)"},{"time":"第四小节(11:10-12:00)","wednesday":"  Java语言程序设计R_08(明向校区行远楼B121)","monday":"   ","friday":"  电路与电子技术R_08(明向校区行知楼B117)","sunday":"   ","thursday":"   ","saturday":"   ","tuesday":"  马克思主义基本原理_12(明向校区行知楼B117)"},{"time":"第五小节(14:00-14:50)","wednesday":"  概率论与数理统计E_06(明向校区行知楼C226)","monday":"   ","friday":"  数据结构R_08(明向校区行知楼B115)","sunday":"   ","thursday":"  大学英语(三)_15(明向校区行远楼B122)","saturday":"   ","tuesday":"  数据结构R_08(明向校区行知楼B115)"},{"time":"第六小节(15:00-15:50)","wednesday":"  概率论与数理统计E_06(明向校区行知楼C226)","monday":"   ","friday":"  数据结构R_08(明向校区行知楼B115)","sunday":"   ","thursday":"  大学英语(三)_15(明向校区行远楼B122)","saturday":"   ","tuesday":"  数据结构R_08(明向校区行知楼B115)"},{"time":"第七小节(16:10-17:00)","wednesday":"  概率论与数理统计E_06(明向校区行知楼C226)","monday":"  形势与政策(三)_11(明向校区行远楼A107)","friday":"  离散结构R_08(明向校区行知楼B114)","sunday":"   ","thursday":"  英语听力(三)_36(明向校区行逸楼C-107)","saturday":"   ","tuesday":"   "},{"time":"第八小节(17:10-18:00)","wednesday":"   ","monday":"  形势与政策(三)_11(明向校区行远楼A107)","friday":"  离散结构R_08(明向校区行知楼B114)","sunday":"   ","thursday":"  英语听力(三)_36(明向校区行逸楼C-107)","saturday":"   ","tuesday":"   "},{"time":"第九小节(19:00-19:50)","wednesday":"   ","monday":"  离散结构R_08(明向校区行知楼A104)","friday":"   ","sunday":"   ","thursday":"   ","saturday":"   ","tuesday":"   "},{"time":"第十小节(20:00-20:50)","wednesday":"   ","monday":"  离散结构R_08(明向校区行知楼A104)","friday":"   ","sunday":"   ","thursday":"   ","saturday":"   ","tuesday":"   "},{"time":"第十一小节(21:00-22:00)","wednesday":"   ","monday":"   ","friday":"   ","sunday":"   ","thursday":"   ","saturday":"   ","tuesday":"   "}]
     * status : success
     */

    private String status;
    private List<TableBean> table;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TableBean> getTable() {
        return table;
    }

    public void setTable(List<TableBean> table) {
        this.table = table;
    }

    public static class TableBean {
        /**
         * time : 第一小节(08:00-08:50)
         * wednesday :    
         * monday :   大学英语(三)_15(明向校区行远楼B122)
         * friday :   Java语言程序设计R_08(明向校区行知楼B115)
         * sunday :    
         * thursday :   电路与电子技术R_08(明向校区行远楼A102)
         * saturday :    
         * tuesday :    
         */

        private String time;
        private String wednesday;
        private String monday;
        private String friday;
        private String sunday;
        private String thursday;
        private String saturday;
        private String tuesday;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWednesday() {
            return wednesday;
        }

        public void setWednesday(String wednesday) {
            this.wednesday = wednesday;
        }

        public String getMonday() {
            return monday;
        }

        public void setMonday(String monday) {
            this.monday = monday;
        }

        public String getFriday() {
            return friday;
        }

        public void setFriday(String friday) {
            this.friday = friday;
        }

        public String getSunday() {
            return sunday;
        }

        public void setSunday(String sunday) {
            this.sunday = sunday;
        }

        public String getThursday() {
            return thursday;
        }

        public void setThursday(String thursday) {
            this.thursday = thursday;
        }

        public String getSaturday() {
            return saturday;
        }

        public void setSaturday(String saturday) {
            this.saturday = saturday;
        }

        public String getTuesday() {
            return tuesday;
        }

        public void setTuesday(String tuesday) {
            this.tuesday = tuesday;
        }
    }

    @Override
    public String toString() {
        return "ClassBean{" +
                "table=" + table +
                '}';
    }
}
