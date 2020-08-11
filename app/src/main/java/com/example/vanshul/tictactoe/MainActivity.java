package com.example.vanshul.tictactoe;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    boolean turn=true;
    //true: gold appears with 0 in array  &  false: black with 1
    MediaPlayer mp;
    Set<Integer> s=new HashSet<Integer>();
    int [] a=new int []{-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int [][] chk={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean musicp=true;
    public void Pause(View view){
        RadioButton btnEng=(RadioButton)findViewById(R.id.rb);
        if(musicp) {
            mp.pause();

            btnEng.setChecked(false);

        }
        else{
            mp.start();
            btnEng.setChecked(true);
        }
        musicp=!musicp;
    }
    public void clk(View view){

        ImageView img=(ImageView)view;
        int tap=Integer.parseInt(img.getTag().toString());
        String player;
        if(!s.contains(tap)) {
            img.animate().alpha(0);
            img.setTranslationY(-1500);
            //img.animate().rotation(-720);
            if (turn == true) {
                img.setImageResource(R.drawable.gold);
                a[tap]=0;
                turn = false;
                player="O";

            } else {
                img.setImageResource(R.drawable.black);
                a[tap]=1;
                turn = true;
                player="X";

            }
            img.animate().alpha(1).translationYBy(1500).rotation(720).setDuration(500);
            //img.animate().alpha(1).rotation(720).setDuration(1000);
            s.add(tap);
            int f=0;
            for(int[] i: chk){
                if(a[i[0]]== a[i[1]] && a[i[0]]==a[i[2]] && a[i[0]]!=-1){
                    TextView txt=(TextView) (findViewById(R.id.textView));
                    txt.setText(player+" has won!!");
                    txt.setVisibility(view.VISIBLE);
                    //Toast.makeText(this, player+"has won", Toast.LENGTH_SHORT).show();
                    for(int ii=0;ii<9;ii++){
                        s.add(ii);
                    }
                    Button b=(Button)(findViewById(R.id.button));
                    b.setVisibility(view.VISIBLE);
                    f=1;
                }
            }
            if(s.size()==9 && f==0 ){
                TextView txt=(TextView) (findViewById(R.id.textView));
                txt.setText("It's a Draw!!");
                txt.setVisibility(view.VISIBLE);
                Button b=(Button)(findViewById(R.id.button));
                b.setVisibility(view.VISIBLE);
            }
        }

    }
    public void rst(View view){
        Button b=(Button)(findViewById(R.id.button));
        b.setVisibility(view.INVISIBLE);
        TextView txt=(TextView) (findViewById(R.id.textView));
        txt.setVisibility(view.INVISIBLE);
        s.clear();
        for(int i=0;i<9;i++){
            a[i]=-1;
        }
        GridLayout g=(GridLayout)(findViewById(R.id.grid));
        for(int i=0;i<g.getChildCount();i++){
            ImageView im=(ImageView)(g.getChildAt(i));
            im.setImageDrawable(null);
        }
        turn=true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp=(MediaPlayer.create(this,R.raw.music));
        mp.start();
    }
}
