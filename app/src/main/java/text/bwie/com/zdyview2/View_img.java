package text.bwie.com.zdyview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class View_img extends View {

    public String centerText = "start";
    public Paint mPaint;

    public View_img(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Paint mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(24);
        Rect rect = new Rect();
        mPaint.getTextBounds(centerText, 0, centerText.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        canvas.drawText(centerText, 300 / 2 - textWidth / 2, 300 / 2 + textHeight / 2, mPaint);
    }
}
