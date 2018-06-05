package com.example.sony.cameraremote;

import java.util.List;

/**
 * desc:
 * Author:cifz
 * time:2018/5/9 10:29
 * e_mail:wangzhen1798@gmail.com
 */

public class SceneEntity2 {

    private List<SceneBean> scene;

    public List<SceneBean> getScene() {
        return scene;
    }

    public void setScene(List<SceneBean> scene) {
        this.scene = scene;
    }

    public static class SceneBean {
        /**
         * aperture : 1.8
         * color_temperature : 5260
         * focal_length : 100
         * id : 33
         * iso_sensitivity : 100
         * light_param : [{"id":1,"time":5000,"type":"enter","value":0},{"id":2,"time":2000,"type":"exit","value":1},{"id":3,"time":10000,"type":"enter","value":0},{"id":4,"time":2000,"type":"exit","value":0},{"id":5,"value":0},{"id":6,"value":0},{"id":7,"value":0},{"id":8,"value":0},{"id":9,"value":1},{"id":10,"value":0},{"id":11,"value":0},{"id":12,"value":0},{"id":13,"value":0},{"id":14,"value":0},{"id":15,"value":0},{"id":16,"value":0}]
         * name : 测试场景
         * picture : http://scene.donlann.com/uploads/scene/d6db9337083c678c49223ac5dfdaf0907f3bd7c2.jpg
         * shutter_speed : 1/100
         * white_balance : Color Temperature
         */

        private String aperture;
        private String color_temperature;
        private String focal_length;
        private int id;
        private String iso_sensitivity;
        private String name;
        private String picture;
        private String shutter_speed;
        private String white_balance;
        private List<LightParamBean> light_param;

        public String getAperture() {
            return aperture;
        }

        public void setAperture(String aperture) {
            this.aperture = aperture;
        }

        public String getColor_temperature() {
            return color_temperature;
        }

        public void setColor_temperature(String color_temperature) {
            this.color_temperature = color_temperature;
        }

        public String getFocal_length() {
            return focal_length;
        }

        public void setFocal_length(String focal_length) {
            this.focal_length = focal_length;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIso_sensitivity() {
            return iso_sensitivity;
        }

        public void setIso_sensitivity(String iso_sensitivity) {
            this.iso_sensitivity = iso_sensitivity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getShutter_speed() {
            return shutter_speed;
        }

        public void setShutter_speed(String shutter_speed) {
            this.shutter_speed = shutter_speed;
        }

        public String getWhite_balance() {
            return white_balance;
        }

        public void setWhite_balance(String white_balance) {
            this.white_balance = white_balance;
        }

        public List<LightParamBean> getLight_param() {
            return light_param;
        }

        public void setLight_param(List<LightParamBean> light_param) {
            this.light_param = light_param;
        }

        public static class LightParamBean {
            /**
             * id : 1
             * time : 5000
             * type : enter
             * value : 0
             */

            private int id;
            private int time;
            private String type;
            private int value;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
