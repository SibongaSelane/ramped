package com.deitel.littlethinkers.Princesses;

import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.deitel.littlethinkers.R;
import com.deitel.littlethinkers.SlidingPuzzles.CustomAdapter;
import com.deitel.littlethinkers.SlidingPuzzles.SlidingPuzzle1;

import java.util.ArrayList;
import java.util.Random;

public class SlidingPuzzlePrincesses extends AppCompatActivity {

    private static final int COLUMNS = 2;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;

    private static String[] tileList;

    private static GridViewPrincesses mGridView;

    private static int mColumnWidth, mColumnHeight;

    Button btnReset;
    public static TextView txtStepCount;
    public static int stepCount = 0;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";

    ImageView imageHint;

    private SoundPool soundPool;
    private int hintSound;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_puzzle_princesses);
        btnReset = findViewById(R.id.btnReset);
        txtStepCount = findViewById(R.id.txtStepCount);

        imageHint = findViewById(R.id.imageHint);


        init();
        scramble();
        setDimensions();

        reset();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        hintSound = soundPool.load(this, R.raw.studenthome,1);

        mediaPlayer = MediaPlayer.create(this,R.raw.puzzlegame);

        Hint();

    }

    private void Hint() {
        imageHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SlidingPuzzlePrincesses.this);

                builder.setMessage("To complete the puzzle all you need to do is slide the puzzle pieces in the " +
                        "direction you would like to move them, until your puzzle looks like the sample picture. \n" +
                        "Click the Play Again button to redo the puzzle.");

                mediaPlayer.start();

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }
    private void reset(){
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
                overridePendingTransition(0,0);

                stepCount = 0;

            }
        });

    }

    private void init() {
        mGridView = (GridViewPrincesses) findViewById(R.id.gridPrincesses);
        mGridView.setNumColumns(COLUMNS);

        tileList = new String[DIMENSIONS];
        for (int i = 0; i < DIMENSIONS; i++) {
            tileList[i] = String.valueOf(i);
        }
    }

    private void scramble() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }


    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display(getApplicationContext());
            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static void display(Context context) {
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length; i++) {
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(R.drawable.princesses1);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(R.drawable.princesses2);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(R.drawable.princesses3);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(R.drawable.princesses4);

            buttons.add(button);
        }

        mGridView.setAdapter(new CustomAdapter(buttons, mColumnWidth, mColumnHeight));
    }

    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);


        if (isSolved()) Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
    }

    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right))
            {
                swap(context, position, 1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(down))
            {
                swap(context, position, COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left))
            {
                swap(context, position, -1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(down))
            {
                swap(context, position, COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(right))
            {
                swap(context, position, 1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left))
            {
                swap(context, position, -1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(down))
            {
                swap(context, position, COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up))
            {
                swap(context, position, -COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(right))
            {
                swap(context, position, 1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(down))
            {
                swap(context, position, COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up))
            {
                swap(context, position, -COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(left))
            {
                swap(context, position, -1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1)
                {
                    swap(context, position,
                            COLUMNS);
                    stepCount = stepCount + 1;
                    txtStepCount.setText(Integer.toString(stepCount));
                }
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up))
            {
                swap(context, position, -COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(right))
            {
                swap(context, position, 1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up))
            {
                swap(context, position, -COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(left))
            {
                swap(context, position, -1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(right))
            {
                swap(context, position, 1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up))
            {
                swap(context, position, -COLUMNS);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(left))
            {
                swap(context, position, -1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else if (direction.equals(right))
            {
                swap(context, position, 1);
                stepCount = stepCount + 1;
                txtStepCount.setText(Integer.toString(stepCount));
            }
            else swap(context, position, COLUMNS);
        }
    }

    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;
    }
}