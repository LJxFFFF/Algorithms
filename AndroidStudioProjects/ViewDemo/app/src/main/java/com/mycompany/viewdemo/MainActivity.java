package com.mycompany.viewdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recyclerView = (RecyclerView) findViewById(R.id.rcv);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
         public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("请输入城市名称");
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setView(new EditText(MainActivity.this));
            builder.setPositiveButton("确定", null);
            builder.setNegativeButton("取消", null);
            AlertDialog dialog=builder.create();
            dialog.show();
            Log.d("OK", "click");
        }

        return super.onOptionsItemSelected(item);
    }

    public class CounterView extends View implements View.OnClickListener {

        private Paint mPaint;

        private Rect mBounds;

        private int mCount;

        public CounterView(Context context, AttributeSet attrs) {
            super(context,attrs);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBounds = new Rect();
            setOnClickListener(this);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
            mPaint.setColor(Color.YELLOW);
            mPaint.setTextSize(30);
            String text = String.valueOf(mCount);
            mPaint.getTextBounds(text, 0, text.length(), mBounds);
            float textWidth = mBounds.width();
            float textHeight = mBounds.height();
            canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, mPaint);
        }

        @Override
        public void onClick(View v) {
            mCount++;
            invalidate();
        }
    }
}
