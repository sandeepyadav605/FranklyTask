package com.taskfrankly;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.GridView;
import android.widget.TextView;

import com.taskfrankly.adapter.CustomGridAdapter;

public class GridPatternActivity extends Activity {
	Handler handler = new Handler(); 
	private ArrayList<Integer> mainDataList =new ArrayList<Integer>();
	private GridView gridview;
    CustomGridAdapter gridAdapter;
	private int dimen;
	private static final int SLEEP_TIME=300;
	private TextView numberTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout_activity);

		if(null !=getIntent().getExtras()){
			dimen=Integer.parseInt(getIntent().getExtras().getString(InputNumberActivity.EXTRA_INPUT_NUM));
		}

		initUIControls();
	}

	//Initialize the ui element with respective ids.
	private void initUIControls() {
		gridview = (GridView) findViewById(R.id.gridview);
		numberTextView=(TextView)findViewById(R.id.matrixforTv);
		numberTextView.setText("For n="+dimen);

		gridview.setNumColumns(dimen);

		gridAdapter=new CustomGridAdapter(this, mainDataList);
		gridview.setAdapter(gridAdapter);

		fillDataWithTimer();
	}


	
	private void fillDataWithTimer(){
		Thread thread = new Thread(){
			public void run(){
				try{
					buildData(dimen,this);
					
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		};

		thread.start();
	}

	private void buildData(int arrNum,Thread t) throws InterruptedException{

		final int n=arrNum;
		final int mainArray[][]=new int[n][n];
		int indexCounter=1, firstCol=0, lastCol=n-1, firstRow=0, lastRow=n-1;

		while(indexCounter<=n*n)
		{
			for(int i=firstCol;i<=lastCol;i++)
			{
				mainArray[firstRow][i]=indexCounter;
				indexCounter++;

				//sleep and print number
				Thread.sleep(SLEEP_TIME);
				printNumber(n,mainArray);
			}

			for(int j=firstRow+1;j<=lastRow;j++)
			{
				mainArray[j][lastCol]=indexCounter;
				indexCounter++;

				//sleep and print number
				Thread.sleep(SLEEP_TIME);
				printNumber(n,mainArray);
			}

			for(int i=lastCol-1;i>=firstCol;i--)
			{
				mainArray[lastRow][i]=indexCounter;
				indexCounter++;

				//sleep and print number
				Thread.sleep(SLEEP_TIME);
				printNumber(n,mainArray);

			}

			for(int j=lastRow-1;j>=firstRow+1;j--)
			{
				mainArray[j][firstCol]=indexCounter;
				indexCounter++;

				//sleep and print number
				Thread.sleep(SLEEP_TIME);
				printNumber(n,mainArray);
			}

			firstCol++;
			lastCol--;
			firstRow++;
			lastRow--;

		}
	}



	private void printNumber(final int n, final int[][] resultArray){
		mainDataList.clear();
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				mainDataList.add(resultArray[i][j]);
			}
		} 

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				gridAdapter=new CustomGridAdapter(GridPatternActivity.this, mainDataList);
				gridview.setAdapter(gridAdapter);
				
				// we also can use notifydatasetchanged here but have to choose different data structure.
			}
		});


	}

}