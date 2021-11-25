//package com.example.taobaithu.common;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Point;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.load.engine.Resource;
//import com.example.taobaithu.R;
//import com.example.taobaithu.callback.MyButtonClickListenner;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Queue;
//
//public class MySwipeHelper extends ItemTouchHelper.SimpleCallback {
//    int buttonWidth;
//    private RecyclerView recyclerView;
//    private List<MyButton> buttonList;
//    private GestureDetector gestureDetector;
//    private int swipePostion = -1;
//    private float swipeThreshold = 0.5f;
//    private Map<Integer, List<MyButton>> buttonbuffer;
//    private Queue<Integer> removequee;
//
//    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
//        @Override
//        public boolean onSingleTapUp(MotionEvent e) {
//            for (MyButton button : buttonList)
//                if (button.onClick(e.getX(), e.getY()))
//                    break;
//
//            return true;
//        }
//    };
//
//    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (swipePostion < 0) return false;
//            Point point = new Point((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
//            RecyclerView.ViewHolder swipeViewholer = recyclerView.findViewHolderForAdapterPosition(swipePostion);
//            View swipeItem = swipeViewholer.itemView;
//            Rect rect = new Rect();
//            swipeItem.getGlobalVisibleRect(rect);
//            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_UP ||
//                    motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
//                /** Video #17 đang làm dở 13:43s **/
//                if (rect.top < point.y && rect.bottom > point.y) {
//                    gestureDetector.onTouchEvent(motionEvent);
//                } else {
//                    removequee.add(swipePostion);
//                    swipePostion = -1;
//                }
//                return false;
//            }
//
//        }
//
//        ;
//    }
//
//
//    public MySwipeHelper(Context context, RecyclerView recyclerView, int buttonWidth) {
//        super(0, ItemTouchHelper.LEFT);
//        this.recyclerView = recyclerView;
//        this.buttonList = new ArrayList<>();
//        this.gestureDetector = new GestureDetector(context, gestureListener);
//        this.recyclerView.setOnTouchListener(onTouchListener);
//        this.buttonbuffer = new HashMap<>();
//        this.buttonWidth = buttonWidth;
//        removequee = new LinkedList<Integer>() {
//            @Override
//            public boolean add(Integer integer) {
//                if (contains(integer))
//                    return false;
//                return super.add(integer);
//            }
//        };
//        attachWipe();
//    }
//
//    private void attachWipe() {
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(this);
//        itemTouchHelper.attachToRecyclerView(recyclerView);
//    }
//
//    private synchronized void recoverSwipeItem() {
//        while (!removequee.isEmpty()) {
//            int pos = removequee.poll();
//            if (pos > -1)
//                recyclerView.getAdapter().notifyItemChanged(pos);
//        }
//    }
//
//    public class MyButton {
//        private String text;
//        private int imageResId, textSize, color, pos;
//        private RectF clickRegion;
//        private MyButtonClickListenner listenner;
//        private Context context;
//        private Resources resources;
//
//
//        public MyButton(Context context, String text, int textSize, int imageResId, int color, MyButtonClickListenner listenner) {
//            this.text = text;
//            this.imageResId = imageResId;
//            this.textSize = textSize;
//            this.color = color;
//            this.listenner = listenner;
//            this.context = context;
//        }
//
//        public boolean onClick(float x, float y) {
//            if (clickRegion != null && clickRegion.contains(x, y)) {
//                listenner.onClick(pos);
//                return true;
//            }
//            return false;
//        }
//
//        public void onDraw(Canvas c, RectF rectF, int pos) {
//            Paint p = new Paint();
//            p.setColor(color);
//            c.drawRect(rectF, p);
//            //Text
//            p.setColor(Color.WHITE);
//            p.setTextSize(textSize);
//            Rect r = new Rect();
//            float cHeight = rectF.height();
//            float cWidth = rectF.width();
//            p.setTextAlign(Paint.Align.LEFT);
//            p.getTextBounds(text, 0, text.length(), r);
//            float x = 0, y = 0;
//            if (imageResId == 0) //If just show text
//            {
//                x = cWidth / 2f - r.width() / 2f - r.left;
//                y = cHeight / 2f - r.height() / 2f - r.bottom;
//                c.drawText(text, rectF.left + x, rectF.top + y, p);
//            } else //if have image resource
//            {
//                Drawable d = ContextCompat.getDrawable(context, imageResId);
//                Bitmap bitmap = drawbleToBitMap(d);
//                c.drawBitmap(bitmap, (rectF.left + rectF.right) / 2, (rectF.top + rectF.bottom) / 2, p);
//            }
//            clickRegion = rectF;
//            this.pos = pos;
//        }
//    }
//
//    private Bitmap drawbleToBitMap(Drawable d) {
//        if (d instanceof BitmapDrawable)
//            return ((BitmapDrawable) d).getBitmap();
//        Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        d.draw(canvas);
//        return bitmap;
//    }
//
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//        int pos = viewHolder.getAdapterPosition();
//        if (swipePostion != pos)
//            removequee.add(swipePostion);
//        swipePostion = pos;
//        if (buttonbuffer.containsKey(swipePostion))
//            buttonList = buttonbuffer.get(swipePostion);
//        else
//            buttonList.clear();
//        buttonbuffer.clear();
//        swipeThreshold = 0.5f * buttonList.size() * buttonWidth;
//        recoverSwipeItem();;
//    }
//
//    @Override
//    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
//        return swipeThreshold;
//    }
//
//    @Override
//    public float getSwipeEscapeVelocity(float defaultValue) {
//        return 0.1f*defaultValue;
//    }
//
//    @Override
//    public float getSwipeVelocityThreshold(float defaultValue) {
//        return 5.0f*defaultValue;
//    }
//
//    @Override
//    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        int pos = viewHolder.getAdapterPosition();
//        float translationX = dX;
//        View itemView = viewHolder.itemView;
//        if(pos<0){
//            swipePostion = pos;
//            return;
//        }
//        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
//            if(dX<0){
//                List<MyButton> buffer = new ArrayList<>();
//                if(!buttonbuffer.containsKey(pos)){
//                    instantiateMyButton(viewHolder,buffer);
//                    buttonbuffer.put(pos,buffer);
//                }
//                else
//                {
//                    buffer = buttonbuffer.get(pos);
//                }
//            }
//        }
//    }
//}