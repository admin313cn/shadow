package cn.u313.plugin.model;

public class GxConfig {

    /**
     * data : {"goin":true,"gxId":0,"gxText":"无","noticeId":0,"noticeText":"无","p_skey":"","sign":134522,"splash":"http://y.313u.cn/splash/1EB9BA6835F2726901B939A2EBA46D82.jpg"}
     * time : 1591337087853
     * version : 1542
     */

    private DataBean data;
    private long time;
    private String version;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {
        /**
         * goin : true
         * gxId : 0
         * gxText : 无
         * noticeId : 0
         * noticeText : 无
         * p_skey :
         * sign : 134522
         * splash : http://y.313u.cn/splash/1EB9BA6835F2726901B939A2EBA46D82.jpg
         */

        private boolean goin;
        private int gxId;
        private String gxText;
        private int noticeId;
        private String noticeText;
        private String p_skey;
        private int sign;
        private String splash;

        public boolean isGoin() {
            return goin;
        }

        public void setGoin(boolean goin) {
            this.goin = goin;
        }

        public int getGxId() {
            return gxId;
        }

        public void setGxId(int gxId) {
            this.gxId = gxId;
        }

        public String getGxText() {
            return gxText;
        }

        public void setGxText(String gxText) {
            this.gxText = gxText;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public String getNoticeText() {
            return noticeText;
        }

        public void setNoticeText(String noticeText) {
            this.noticeText = noticeText;
        }

        public String getP_skey() {
            return p_skey;
        }

        public void setP_skey(String p_skey) {
            this.p_skey = p_skey;
        }

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }

        public String getSplash() {
            return splash;
        }

        public void setSplash(String splash) {
            this.splash = splash;
        }
    }
}
