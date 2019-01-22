package com.hit.controller;

import java.util.Observable;
import java.util.Observer;

import com.hit.model.Model;
import com.hit.view.View;

public class CacheUnitController implements Controller {
	
	View view;
	Model model;
	
	public CacheUnitController(View view, Model model) {
		 this.view = view;
		 this.model = model;
	}

	@Override
	public void update(Observable observable, Object arg) {
		
		String stringArg = (String) arg;
	
		if (observable instanceof Model) {
			System.out.println("controller - model sent a message ");
			System.out.println("message = " + stringArg);	
			
			this.view.updateUIData(stringArg);
			
		} else if (observable instanceof View) {
			System.out.println("controller - view sent a message");
			
			this.model.updateModelData(stringArg);
		}
	}
}
