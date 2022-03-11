package cn.u313.plugin.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;

/**
 * 新版diy弹窗
 */
public class DIYDialog extends Dialog {
    private Context context;

    public DIYDialog(Context context) {
        super(context);
        this.context = context;
    }
    TextView title; //标题
    TextView text; //内容
    Button close;  //关闭按钮
    Button aTrue;   //确定
    TextView dangGe;     //当个按钮
    DialogOncilk dialogOncilk;  //点击的事件
    View registerView;     // 布局
    BounceScrollView bounceScrollView;
    boolean isEv=true;

    private String t_title;
    private String t_text;
    private String t_close;
    private String t_aTrue;
    private int height=100;
    private String t_dangGe;
    boolean dialog_ev=false;

    public DIYDialog setClick(DialogOncilk d){
        dialogOncilk=d;
        return this;
    }

    /**
     * 是否为单个btn
     * @param b
     * @return
     */
    public DIYDialog setDialogEv(boolean b){
        dialog_ev=b;
        return this;
    }
    /**
     * 点击空白关闭
     * @param d
     * @return
     */
    public DIYDialog setEv(boolean d){
        isEv=d;
        return this;
    }
    public DIYDialog setTitle(String title) {
        this.t_title=(title);
        return this;
    }

    public DIYDialog setTextViewHeight(int height) {
        this.height=(height);
        return this;
    }
    public DIYDialog setText(String text) {
//        t_text=text;
        this.t_text=(text);
        return this;
    }
    public DIYDialog setText(String text, int heihgt) {
//        t_text=text;
        setTextViewHeight(heihgt);
        this.t_text=(text);
        return this;
    }
    public DIYDialog setBtnText(String t_close, String t_aTrue) {
        this.t_close=(t_close);
        this.t_aTrue=(t_aTrue);
        return this;
    }
    public DIYDialog setBtnText(String t_close) {
        setDialogEv(true);
        this.t_dangGe=(t_close);
        return this;
    }
    @Override
    public void show() {

        super.show();
        title.setText(t_title);
        text.setText(Html.fromHtml(t_text));
        close.setText(t_close);
        aTrue.setText(t_aTrue);
        dangGe.setText(t_dangGe);
        //设置高度
//        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) bounceScrollView.getLayoutParams(); // 取控件mGrid当前的布局参数
//        linearParams.height = height;// 当控件的高强制设成75象素
//        bounceScrollView.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件mGrid2
        if(dialogOncilk==null)
            //设置默认点击事件
            dialogOncilk=new DialogOncilk() {
                @Override
                public void close() {

                }

                @Override
                public void ok() {

                }

                @Override
                public void Exception(Exception e) {

                }
            };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        registerView = View.inflate(this.context, R.layout.notice_temp, null);
        setContentView(registerView);
        // 这句话起全屏的作用
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        initView();
        initListener();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(isEv)
            this.dismiss();
        return super.onTouchEvent(event);
    }

    private void initListener() {

    }

    private void initView() {
        dangGe = findViewById(R.id.notice_btn_okOne);
        title =  findViewById(R.id.notice_title);
        text =  findViewById(R.id.notice_text);
        close =  findViewById(R.id.notice_btn_no);
        aTrue =  findViewById(R.id.notice_btn_ok);
        bounceScrollView = findViewById(R.id.BounceScrollView);
        findViewById(R.id.view_twoBtn).setVisibility(!dialog_ev? View.VISIBLE: View.GONE);
        findViewById(R.id.view_oneBtn).setVisibility(dialog_ev? View.VISIBLE: View.GONE);
        findViewById(R.id.notice_context).setOnClickListener(v -> Log.e(TAG, "onClick: " ));//去除焦点
        close.setOnClickListener(v -> {
            dialogOncilk.close();
            //dismiss();
        });
        aTrue.setOnClickListener(v -> {
            dialogOncilk.ok();
            dismiss();
        });
        dangGe.setOnClickListener(v -> {
            dialogOncilk.ok();
            dismiss();
        });

    }
}
