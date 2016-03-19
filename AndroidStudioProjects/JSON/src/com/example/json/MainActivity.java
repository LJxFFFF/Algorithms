package com.example.json;

import java.io.StringReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	
	private Button button1;
	
	final int[] names = new int[] {
			R.id.view1,
			R.id.view2,
			R.id.view3,
			R.id.view4,
			R.id.view5,
			R.id.view6,
	};
	
	TextView[] views = new TextView[names.length];
	private String jsonData = "[{\"����\":\"����\",\"ѧ��\":\"001\"},{\"����\":\"����\",\"ѧ��\":\"002\"},{\"����\":\"����\",\"ѧ��\":\"003\"}]" ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(new ButtonListener());
		
	}
	
	private class ButtonListener implements OnClickListener{
		@Override
	    public void onClick(View v) {
			JsonUtils jsonUtils = new JsonUtils();
			jsonUtils.parseJson(jsonData);
			
			}
		}
	
	

    public class JsonUtils {
    	int i=0;
		public void parseJson(String jsonData) {
			try{
				JsonReader reader = new JsonReader(new StringReader(jsonData));
				
				reader.beginArray();
				while(reader.hasNext()) {
					reader.beginObject();
					while(reader.hasNext()) {
						String tagName = reader.nextName();
				        
						
						if(tagName.equals("����")) {
								views[i] = (TextView)findViewById(names[i]);
								views[i].setText("����: " +"  " +  reader.nextString());
							  
					    	//view1 = (TextView)findViewById(R.id.view1);
					    	//view1.setText("����: " +"  " +  reader.nextString());
					    	//System.out.println("����: " +"  " +  reader.nextString());
					    }
					   
					    
					    else  
					    	if(tagName.equals("ѧ��")) {
									views[i] = (TextView)findViewById(names[i]);
									views[i].setText("ѧ��:" + "   " +  reader.nextInt());
								    
					    	    //view2 = (TextView)findViewById(R.id.view2);
					    	    //view2.setText("ѧ��:" + "  " +  reader.nextInt());
					    	    //System.out.println("ѧ��:" + "  " +  reader.nextInt());
						 
					    	}
						i++;
						}
				    
					reader.endObject();
			     }
				reader.endArray();
		     }
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
		

