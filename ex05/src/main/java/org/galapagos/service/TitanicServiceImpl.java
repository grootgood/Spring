package org.galapagos.service;

import java.io.FileInputStream;

import org.galapagos.domain.TitanicVO;
import org.springframework.stereotype.Service;

import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

@Service
public class TitanicServiceImpl implements TitanicService{
	
	Classifier model;
	Instances dataSet;
	
	public TitanicServiceImpl() {
		// 모델 불러오기
		try {
			String[] classLabels = {"no", "yes"};
			dataSet = WekaUtil.makeInstances(7, classLabels);
			
			model = (Classifier)SerializationHelper.read(new FileInputStream("c:/Temp/titanic_pre2.model")); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String predict(TitanicVO value) {
		
		Instance data = value.toInstance(); // 가중치, 값배열
		data.setDataset(dataSet);
		
		// 분류하기
		double result = 0;
		try {
			result = model.classifyInstance(data);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return dataSet.classAttribute().value((int)result);
	}
}
