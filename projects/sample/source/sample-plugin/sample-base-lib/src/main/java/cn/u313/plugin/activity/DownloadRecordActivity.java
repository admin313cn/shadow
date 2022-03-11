package cn.u313.plugin.activity;

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;

import java.util.List;

public class DownloadRecordActivity extends BaseActivity {
    ListView listView;

    List<DownloadEntity> downloadBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_record);

        TextView text000=findViewById(R.id.text00);
        listView=findViewById(R.id.list);
        downloadBean = Aria.download(this).getAllCompleteTask();
        if(downloadBean==null||downloadBean.size()==0){
            text000.setVisibility(View.VISIBLE);

            return;
        }
//        text
        listView.setAdapter(new ArrayAdapter<DownloadEntity>(getApplicationContext(), R.layout.list,downloadBean){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.list,parent,false);
                TextView textView=view.findViewById(R.id.text);
                textView.setText(downloadBean.get(position).getFileName());
                LinearLayout linearLayout=view.findViewById(R.id.dlon);
                TextView tn=view.findViewById(R.id.dlno);
                tn.setVisibility(View.VISIBLE);
                tn.setText("已完成");

                    textView.setTextColor(Color.BLACK);
                    tn.setTextColor(Color.BLACK);

                linearLayout.setVisibility(View.GONE);
                return view;
            }
        });

        //////2021-04-19 18:09:46.510 16816-16816/cn.u313.music W/System.er
        // r: downloadEntity.getServerFileName()Music{id=null, type=0, songId=0, title='玫瑰花
        // 的葬礼', artist='许嵩', album='Vae新歌%2B精选珍藏合辑', albumId=0, coverPath='null', duration=0, path
        // ='/storage/emulated/0/music/song/玫瑰花的葬礼-许嵩.mp3/null', fileName='null', fileSize=4148043, bitmap=null, a='null', time=0}
        //播放
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(), "播放视频"+downloadBean.get(position), Toast.LENGTH_SHORT).show();
//            DownloadEntity downloadEntity = downloadBean.get(position);
//            Music music=new Music();
//            //Music{id=2, type=0, songId=1684055,
//            // title='给我一首歌的时间', artist='周杰伦 ',
//            // album='魔杰座', albumId=338, coverPath='null',
//            // duration=253605, path='/storage/emulated/0/music/song/给我一首歌的时间-周杰伦.mp3',
//            // fileName='给我一首歌的时间-周杰伦.mp3', fileSize=4137580, bitmap=null, a='null', time=0}
//            DownInfo downInfo=new Gson().fromJson(downloadEntity.getStr(), DownInfo.class);
//            music.setTitle(downInfo.getTitle());
//            music.setArtist(downInfo.getArtist());
//            music.setAlbum(downInfo.getAlbum());
//            music.setPath(downloadEntity.getFilePath());
//            music.setFileName(downloadEntity.getFilePath().split("/")[downloadEntity.getFilePath().split("/").length-1]);
//            music.setFileSize(downloadEntity.getFileSize());
//            System.err.println("downloadEntity.getServerFileName()"+music);
//            ToastUtils.show("已添加正在播放列表");
//
//            AudioPlayer.get().addAndPlay(music,1);
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}