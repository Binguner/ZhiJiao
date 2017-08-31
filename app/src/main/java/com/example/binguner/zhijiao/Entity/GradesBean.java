package com.example.binguner.zhijiao.Entity;

import java.util.List;

/**
 * Created by binguner on 2017/8/26.
 */

public class GradesBean {
    /**
     * message : 查询成功
     * info : [{"name":"大学英语(一)","credit":"3.5","property":"通识任选","score":"88.0 "},{"name":"体育(一)","credit":"2","property":"综合必修","score":"60.0 "},{"name":"军事理论","credit":"2","property":"综合必修","score":"77.0 "},{"name":"程序设计技术B","credit":"3.5","property":"综合必修","score":"88.0 "},{"name":"高等数学E(一)","credit":"5.5","property":"综合必修","score":"60.0 "},{"name":"计算机基础","credit":"2.5","property":"综合必修","score":"82.0 "},{"name":"思想道德修养与法律基础","credit":"3","property":"通识必修","score":"73.0 "},{"name":"中国近现代史纲要","credit":"2","property":"通识必修","score":"81.0 "},{"name":"大学英语(二)","credit":"3.5","property":"通识任选","score":"69.0 "},{"name":"大学物理B","credit":"4.5","property":"通识必修","score":"87.0 "},{"name":"线性代数E","credit":"2.5","property":"通识必修","score":"81.0 "},{"name":"高等数学E(二)","credit":"5.5","property":"通识必修","score":"82.0 "},{"name":"体育(二)","credit":"1","property":"综合必修","score":"90.0 "},{"name":"面向对象程序设计基础R","credit":"2.5","property":"学科选修","score":"95.0 "},{"name":"软件工程教学实习","credit":"1","property":"实践环节","score":"80.0 "}]
     */

    private String message;
    private List<InfoBean> info;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * name : 大学英语(一)
         * credit : 3.5
         * property : 通识任选
         * score : 88.0 
         */

        private String name;
        private String credit;
        private String property;
        private String score;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
