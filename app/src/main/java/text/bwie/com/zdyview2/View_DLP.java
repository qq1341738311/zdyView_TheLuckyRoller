package text.bwie.com.zdyview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import java.util.Random;

public class View_DLP extends View implements View.OnClickListener{
    private String[] contents = new String[]{"美 女", "女 神", "热 舞", "丰 满", "性 感", "知 性"};
    public int[] colors = new int[]{Color.parseColor("#8EE5EE"), Color.parseColor("#FFD700"), Color.parseColor("#FFD39B"), Color.parseColor("#FF8247"), Color.parseColor("#FF34B3"), Color.parseColor("#F0E68C")};
    public String centerText = "start";
    private int mWidth;
    private Paint mPaint;
    private Context mContext;
    private RotateAnimation rotateAnimation;
    private float newdul;

    public View_DLP(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;
        mPaint = new Paint();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔颜色
        mPaint.setColor(Color.YELLOW);
        //画圆
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);
        //写一个跟圆一样占满全屏
        RectF rectF = new RectF(0, 0, mWidth, mWidth);
        //再次定义一下画笔
        mPaint.setStyle(Paint.Style.FILL);
        //设置弧和弧的颜色
        for (int i = 0; i < colors.length; i++) {
            //设置弧的颜色为我们数组中定义的颜色
            mPaint.setColor(colors[i]);
            //弧度设置为i * 60
            int startjd = i * 60;
            canvas.drawArc(rectF, startjd, 60, true, mPaint);
        }

        //再次定义一下画笔
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(25);
        //设置数据
        for (int i = 0; i < contents.length; i++) {
            int startjd = i * 60;
            Path path = new Path();
            //文字位置 rectF ，startjd 所占的角度  ， 60  文字所占大小
            path.addArc(rectF,startjd,60);
            //文字水平和垂直方向的偏移量
            canvas.drawTextOnPath(contents[i],path,50,50,mPaint);
        }

        /*mPaint.setColor(Color.GREEN);
        //canvas.drawCircle(mWidth / 2,mWidth / 2, 50,mPaint);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(24);
        Rect rect = new Rect();
        mPaint.getTextBounds(centerText, 0, centerText.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        canvas.drawText(centerText, mWidth / 2 - textWidth / 2, mWidth / 2 + textHeight / 2, mPaint);*/
    }

    //测量宽高的方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(300, 300);
        //得到测量过后的宽和高      注意这是得到测量过后的宽和高使用getMeasuredWidth()方法
        mWidth = getMeasuredWidth();
    }

    @Override
    public void onClick(View view) {
        //Random  定义一个随机数
        Random random = new Random();
        //nextInt  定义随机数取0-1000之间
        float dul = random.nextInt(1000);
        //其实在这里如果不加这个值的话  可能会出现随机顺时针逆时针的情况   然后下边是解决方案
        //rotateAnimation = new RotateAnimation(newdul, dul, mWidth / 2,mWidth / 2);
        rotateAnimation = new RotateAnimation(newdul, dul+1000, mWidth / 2, mWidth / 2);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(3000);
        startAnimation(rotateAnimation);
        //将随机数赋值给这个成员变量   便于下次点击在结束的地方开始抽奖
        newdul = dul % 360 +1000;
    }
}
