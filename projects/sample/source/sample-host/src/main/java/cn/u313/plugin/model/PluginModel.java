package cn.u313.plugin.model;

public class PluginModel {
    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getPluginid() {
        return pluginid;
    }

    public void setPluginid(Integer pluginid) {
        this.pluginid = pluginid;
    }

    public String getPluginurl() {
        return pluginurl;
    }

    public void setPluginurl(String pluginurl) {
        this.pluginurl = pluginurl;
    }

    public Integer getManagerid() {
        return managerid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public String getManagerurl() {
        return managerurl;
    }

    public void setManagerurl(String managerurl) {
        this.managerurl = managerurl;
    }

    //    @com.fasterxml.jackson.annotation.JsonProperty("uuid")
    private Integer uuid;
//    @com.fasterxml.jackson.annotation.JsonProperty("pluginid")
    private Integer pluginid;
//    @com.fasterxml.jackson.annotation.JsonProperty("pluginurl")
    private String pluginurl;
//    @com.fasterxml.jackson.annotation.JsonProperty("managerid")
    private Integer managerid;
//    @com.fasterxml.jackson.annotation.JsonProperty("managerurl")
    private String managerurl;
}
