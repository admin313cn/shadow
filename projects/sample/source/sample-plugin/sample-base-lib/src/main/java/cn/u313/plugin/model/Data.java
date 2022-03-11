package cn.u313.plugin.model;

public class Data {

    /**
     * status : 101
     * msg : 获取成功
     * data : {"title":"蚊子都会追人跑了，gei gei们还在等什么啊？#影子 #我的生活日记","image":"https://p3-sign.douyinpic.com/tos-cn-p-0015/6832ef2d8ed34760a65c99a619ce554d_1632046336~tplv-dy-360p.jpeg?x-expires=1634108400&x-signature=6dSejSILg%2FHaTknM2k16jKjNwZ8%3D&from=4257465056&s=&se=false&sh=&sc=&l=202109291536030102121640232A00CFA7&biz_tag=feed_cover","video":"http://v3-dy.ixigua.com/c2e10ed4d276ae7d234f2d5fd2fd5814/61542584/video/tos/cn/tos-cn-ve-15/d460cdc3b4dd4ed893de4da4b28e88e7/?a=1128&br=4090&bt=4090&cd=0%7C0%7C0&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=XEy-Iqq3mw3PSYITz7VFAYiUfTus2u6VWy&l=202109291536030102120552012D02B9EB&lr=&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajd1bmQ6Zm1qODMzNGkzM0ApOGU7ZGlmO2Q5Nzg6ZGZoZmdmcDExcjRfajBgLS1kLTBzcy1iYGMuL18vMDA0NTFiNmM6Yw%3D%3D&vl=&vr=","url":"http://v3-dy.ixigua.com/c2e10ed4d276ae7d234f2d5fd2fd5814/61542584/video/tos/cn/tos-cn-ve-15/d460cdc3b4dd4ed893de4da4b28e88e7/?a=1128&br=4090&bt=4090&cd=0%7C0%7C0&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=XEy-Iqq3mw3PSYITz7VFAYiUfTus2u6VWy&l=202109291536030102120552012D02B9EB&lr=&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajd1bmQ6Zm1qODMzNGkzM0ApOGU7ZGlmO2Q5Nzg6ZGZoZmdmcDExcjRfajBgLS1kLTBzcy1iYGMuL18vMDA0NTFiNmM6Yw%3D%3D&vl=&vr="}
     */

    private int status;
    private String msg;
    private DataBean data;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 蚊子都会追人跑了，gei gei们还在等什么啊？#影子 #我的生活日记
         * image : https://p3-sign.douyinpic.com/tos-cn-p-0015/6832ef2d8ed34760a65c99a619ce554d_1632046336~tplv-dy-360p.jpeg?x-expires=1634108400&x-signature=6dSejSILg%2FHaTknM2k16jKjNwZ8%3D&from=4257465056&s=&se=false&sh=&sc=&l=202109291536030102121640232A00CFA7&biz_tag=feed_cover
         * video : http://v3-dy.ixigua.com/c2e10ed4d276ae7d234f2d5fd2fd5814/61542584/video/tos/cn/tos-cn-ve-15/d460cdc3b4dd4ed893de4da4b28e88e7/?a=1128&br=4090&bt=4090&cd=0%7C0%7C0&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=XEy-Iqq3mw3PSYITz7VFAYiUfTus2u6VWy&l=202109291536030102120552012D02B9EB&lr=&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajd1bmQ6Zm1qODMzNGkzM0ApOGU7ZGlmO2Q5Nzg6ZGZoZmdmcDExcjRfajBgLS1kLTBzcy1iYGMuL18vMDA0NTFiNmM6Yw%3D%3D&vl=&vr=
         * url : http://v3-dy.ixigua.com/c2e10ed4d276ae7d234f2d5fd2fd5814/61542584/video/tos/cn/tos-cn-ve-15/d460cdc3b4dd4ed893de4da4b28e88e7/?a=1128&br=4090&bt=4090&cd=0%7C0%7C0&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=XEy-Iqq3mw3PSYITz7VFAYiUfTus2u6VWy&l=202109291536030102120552012D02B9EB&lr=&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajd1bmQ6Zm1qODMzNGkzM0ApOGU7ZGlmO2Q5Nzg6ZGZoZmdmcDExcjRfajBgLS1kLTBzcy1iYGMuL18vMDA0NTFiNmM6Yw%3D%3D&vl=&vr=
         */

        private String title;
        private String image;
        private String video;
        private String url;



        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
