package cn.u313.plugin.view;

import android.content.Context;
import android.widget.Toast;

public interface DialogOncilk  {
    public void close();
    public void ok();
    public void Exception(Exception e);
}
abstract class a{
    public void exp(Context context, Exception e){
        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
    }
}
