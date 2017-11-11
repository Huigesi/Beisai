package com.example.userlogin1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class RegisterDialog extends Dialog implements View.OnClickListener{
	private Context context;
	//private TextView title;
	private Button cancel;
	private Button register;
	public EditText rgusernameET;
	public EditText rgpasswordET;
	public EditText emailET;
	private MyCallBack myCallBack;
	

	public RegisterDialog(Context context) {
		super(context);
		this.context=context;	
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		init();
	}

	private void init() {
		//title=(TextView)findViewById(R.id.title);
		cancel=(Button) findViewById(R.id.rg_cancel);
		register=(Button) findViewById(R.id.rg_register);
		rgusernameET=(EditText) findViewById(R.id.rg_user_name);
		rgpasswordET=(EditText) findViewById(R.id.rg_user_psw);
		emailET=(EditText) findViewById(R.id.rg_user_email);
		cancel.setOnClickListener(this);
		register.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.rg_register:
			myCallBack.ok();
			break;
		case R.id.rg_cancel:
			myCallBack.cancel();
			break;
		}
		
	}
	public void setMyCallBack(MyCallBack myCallBack){
		this.myCallBack=myCallBack;
	}

	public interface MyCallBack{
		void ok();
		void cancel();
	}

}
