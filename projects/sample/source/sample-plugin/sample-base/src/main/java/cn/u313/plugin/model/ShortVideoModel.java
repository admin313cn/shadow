package cn.u313.plugin.model;

@lombok.NoArgsConstructor
@lombok.Data
public class ShortVideoModel {
    private Integer code;
    private String msg;
    private String user;
    private Long time;
    private DataDTO data;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class DataDTO {
        private String title;
        private String cover;
        private String url;
        private String r;
    }
}
