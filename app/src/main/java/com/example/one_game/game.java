package com.example.one_game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class game extends AppCompatActivity {
    private ImageButton ib00,ib01,ib02,ib10,ib11,ib12,ib20,ib21,ib22;
    private Button restartBtn;
    private ImageView lastImge;
    TextView timeTv;

    // 每行的图片个数
    private int imageX = 3;
    // 每列的图片个数
    private int imageY = 3;

    //    图片的总数目
    private int imgCount = imageX*imageY;
    //    空白区域的位置
    private int blankSwap = imgCount-1;
    //    初始化空白区域的按钮id
    private int blankImgid = R.id.pt_ib_02x02;

    int time = 0;//计数

    private int[]image = {R.drawable.img_xiaoxiong_00x00,R.drawable.img_xiaoxiong_00x01,R.drawable.img_xiaoxiong_00x02,
            R.drawable.img_xiaoxiong_01x00,R.drawable.img_xiaoxiong_01x01,R.drawable.img_xiaoxiong_01x02,
            R.drawable.img_xiaoxiong_02x00,R.drawable.img_xiaoxiong_02x01,R.drawable.img_xiaoxiong_02x02};

    //    声明上面图片数组下标的数组，随机排列这个数组，九张图片，下标为0-8
    private int[]imageIndex = new int[image.length];

    Handler handler = new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if (msg.what==1) {
                time++;
                timeTv.setText("时间 : "+time+" 秒");
//               指定延时1000毫秒后发送参数what为1的空信息
                handler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initiew();
//        打乱碎片的函数
        disruptRandom();
//        延迟1s后发送参数为1的消息
        handler.sendEmptyMessageDelayed(1,1000);
    }
    private void disruptRandom() {
        //      给下标数组每个元素赋值，下标是i,值就为i
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
//        规定20次，随机选择两个角标对应的值进行交换
        int rand1,rand2;
        for (int j = 0; j < 20; j++) {
//            随机生成第一个角标
//            Math.random()产生的随机数为0~1之间的小数 此处说的0~1是包含左不包含右，即包含0不包含1
//            Math.random()的值域为[0,1)，然后*8就是[0,8)，再int取整最终值域为{0,1,2,3,4,5,,6,7}
            rand1 = (int)(Math.random()*(imageIndex.length-1));
//            第二次随机生成的角标，不能和第一次随机生成的角标相同，如果相同，就不方便交换了
            do {
                rand2 = (int)(Math.random()*(imageIndex.length-1));
//             判断第一次和第二次生成的角标是否相同,不同则break立刻跳出循环，执行swap交换
                if (rand1!=rand2) {
                    break;
                }
//             若第二次生成的与第一次相同，则重新进入do-while循环生成rand2
            }while (true);
//            交换两个角标上对应的值
            swap(rand1,rand2);
        }

//        随机排列到指定的控件上
//        ib00是绑定的第一块图片按钮，设置图片资源，
//        imageIndex[i]就是被打乱的下标，然后image[x]就表示对应下标为x的图片的id
        ib00.setImageResource(image[imageIndex[0]]);
        ib01.setImageResource(image[imageIndex[1]]);
        ib02.setImageResource(image[imageIndex[2]]);
        ib10.setImageResource(image[imageIndex[3]]);
        ib11.setImageResource(image[imageIndex[4]]);
        ib12.setImageResource(image[imageIndex[5]]);
        ib20.setImageResource(image[imageIndex[6]]);
        ib21.setImageResource(image[imageIndex[7]]);
        ib22.setImageResource(image[imageIndex[8]]);
    }
    //交换
    private void swap(int rand1, int rand2) {
        int temp = imageIndex[rand1];
        imageIndex[rand1] = imageIndex[rand2];
        imageIndex[rand2] = temp;
    }

    private void initiew() {
        ib00 = findViewById(R.id.pt_ib_00x00);
        ib01 = findViewById(R.id.pt_ib_00x01);
        ib02 = findViewById(R.id.pt_ib_00x02);
        ib10 = findViewById(R.id.pt_ib_01x00);
        ib11 = findViewById(R.id.pt_ib_01x01);
        ib12 = findViewById(R.id.pt_ib_01x02);
        ib20 = findViewById(R.id.pt_ib_02x00);
        ib21 = findViewById(R.id.pt_ib_02x01);
        ib22 = findViewById(R.id.pt_ib_02x02);
        timeTv = findViewById(R.id.pt_tv_time);
        restartBtn = findViewById(R.id.pt_btn_restart);
        lastImge = findViewById(R.id.pt_iv);
    }

    public void onClick(View view) {
        int id = view.getId();
//        九个按钮执行的点击事件的逻辑应该是相同的，如果有空格在周围，可以改变图片显示的位置，否则点击事件不响应
        switch (id) {
            case R.id.pt_ib_00x00:
                move(R.id.pt_ib_00x00,0);
                break;
            case R.id.pt_ib_00x01:
                move(R.id.pt_ib_00x01,1);
                break;
            case R.id.pt_ib_00x02:
                move(R.id.pt_ib_00x02,2);
                break;
            case R.id.pt_ib_01x00:
                move(R.id.pt_ib_01x00,3);
                break;
            case R.id.pt_ib_01x01:
                move(R.id.pt_ib_01x01,4);
                break;
            case R.id.pt_ib_01x02:
                move(R.id.pt_ib_01x02,5);
                break;
            case R.id.pt_ib_02x00:
                move(R.id.pt_ib_02x00,6);
                break;
            case R.id.pt_ib_02x01:
                move(R.id.pt_ib_02x01,7);
                break;
            case R.id.pt_ib_02x02:
                move(R.id.pt_ib_02x02,8);
                break;


        }
    }

    private void move(int imagebuttonId, int site) {
//        判断选中的图片在第几行,imageX为3，所以进行取整运算
        int sitex = site / imageX;
//        判断选中的图片在第几列,imageY为3，所以进行取模运算
        int sitey = site % imageY;
//        获取空白区域的坐标，blankx为行坐标，blanky为列坐标
        int blankx = blankSwap / imageX;
        int blanky = blankSwap % imageY;
//        可以移动的条件有两个
//        1.在同一行，列数相减，绝对值为1，可移动   2.在同一列，行数相减，绝对值为1，可以移动
        int x = Math.abs(sitex-blankx);
        int y = Math.abs(sitey-blanky);
        if ((x==0&&y==1)||(y==0&&x==1)){
//            通过id，查找到这个可以移动的按钮
            ImageButton clickButton = findViewById(imagebuttonId);
//            将这个选中的图片设为不可见的，即显示为空白区域
            clickButton.setVisibility(View.INVISIBLE);
//            查找到空白区域的按钮
            ImageButton blankButton = findViewById(blankImgid);
//            将空白区域的按钮设置为图片，image[imageIndex[site]就是刚刚选中的图片，因为这在上面disruptRandom()设置过
            blankButton.setImageResource(image[imageIndex[site]]);
//            移动之前是不可见的，移动之后将控件设置为可见
            blankButton.setVisibility(View.VISIBLE);
//            将改变角标的过程记录到存储图片位置的数组当中
            swap(site,blankSwap);
//            新的空白区域位置更新等于传入的点击按钮的位置
            blankSwap = site;
//            新的空白图片id更新等于传入的点击按钮的id
            blankImgid = imagebuttonId;
        }
//      判断本次移动完成后，是否完成了拼图游戏
        judgeGameOver();

    }

    private void judgeGameOver() {
        boolean loop = true;   //定义标志位loop
        for (int i = 0; i < imageIndex.length; i++) {
            if (imageIndex[i]!=i) {
                loop = false;
                break;
            }
        }
        if (loop) {
//            拼图成功了
//            停止计时
            handler.removeMessages(1);
//            拼图成功后，禁止玩家继续移动按钮
            ib00.setClickable(false);
            ib01.setClickable(false);
            ib02.setClickable(false);
            ib10.setClickable(false);
            ib11.setClickable(false);
            ib12.setClickable(false);
            ib20.setClickable(false);
            ib21.setClickable(false);
            ib22.setClickable(false);
//            拼图成功后，第九块空白显示出图片，即下标为8的第九张图片
            ib22.setImageResource(image[8]);
            ib22.setVisibility(View.VISIBLE);
//            弹出提示用户成功的对话框，并且设置确实的按钮

//           第一步：创建AlertDialog.Builder对象
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//          调用setIcon()设置图标，setTitle()或setCustomTitle()设置标题
//           第二步：设置对话框的内容：setMessage()方法来指定显示的内容
            builder.setMessage("恭喜，拼图成功！您用的时间为"+time+"秒")
//           第三步：调用setPositive/Negative/NeutralButton()设置：确定，取消，中立按钮
                    .setPositiveButton("确认",null);
//           第四歩：调用create()方法创建这个对象
            AlertDialog dialog = builder.create();
//           第五歩：调用show()方法来显示我们的AlertDialog对话框
            dialog.show();
        }
    }
    //重新开始
    public void restart(View view) {
//        将状态还原
        restore();
//       将拼图重新打乱
        disruptRandom();
//       停止handler的消息发送
        handler.removeMessages(1);
//       将时间重新归0，并且重新开始计时
        time = 0;
        timeTv.setText("时间 : "+time+" 秒");
//      每隔1s发送参数what为1的消息msg
        handler.sendEmptyMessageDelayed(1,1000);
    }
    private void restore() {
        //      拼图游戏重新开始，允许移动碎片按钮
        ib00.setClickable(true);
        ib01.setClickable(true);
        ib02.setClickable(true);
        ib10.setClickable(true);
        ib11.setClickable(true);
        ib12.setClickable(true);
        ib20.setClickable(true);
        ib21.setClickable(true);
        ib22.setClickable(true);
//        还原被点击的图片按钮变成初始化的模样
        ImageButton clickBtn = findViewById(blankImgid);
        clickBtn.setVisibility(View.VISIBLE);
//        默认隐藏第九张图片
        ImageButton blankBtn = findViewById(R.id.pt_ib_02x02);
        blankBtn.setVisibility(View.INVISIBLE);
//        初始化空白区域的按钮id
        blankImgid = R.id.pt_ib_02x02;
        blankSwap = imgCount - 1;
    }

    public void answer(View view){
        ib00.setImageResource(image[0]);
        ib01.setImageResource(image[1]);
        ib02.setImageResource(image[2]);
        ib10.setImageResource(image[3]);
        ib11.setImageResource(image[4]);
        ib12.setImageResource(image[5]);
        ib20.setImageResource(image[6]);
        ib21.setImageResource(image[7]);
        ib22.setImageResource(image[8]);
        ib22.setVisibility(View.VISIBLE);

        //           第一步：创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//          调用setIcon()设置图标，setTitle()或setCustomTitle()设置标题
//           第二步：设置对话框的内容：setMessage()方法来指定显示的内容
        builder.setMessage("恭喜，拼图成功！您用的时间为"+time+"秒")
//           第三步：调用setPositive/Negative/NeutralButton()设置：确定，取消，中立按钮
                .setPositiveButton("确认",null);
//           第四歩：调用create()方法创建这个对象
        AlertDialog dialog = builder.create();
//           第五歩：调用show()方法来显示我们的AlertDialog对话框
        dialog.show();

    }
}
