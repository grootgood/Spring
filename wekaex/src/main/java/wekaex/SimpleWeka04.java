package wekaex;

import java.io.FileInputStream;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class SimpleWeka04 {

	public static void main(String[] args) throws Exception {
		
		String[] classLabels = {"SG", "C"};
		Instances dataSet = WekaUtil.makeInstances(6, classLabels); // 7은 속성 개수
		
		// 모델 불러오기
		Classifier model = (Classifier) SerializationHelper.read(new FileInputStream("c:/Temp/basketball.model"));
		
		// 분류할 데이터 준비
		double[] values = new double[] {2.0, 2.9, 3.3, 2.5, 1.2, 0.2}; // SG
		Instance data1 = new DenseInstance(1,values); // 가중치, 값배열
		data1.setDataset(dataSet);
		
		// 분류하기
		double result = model.classifyInstance(data1);
		
		// 정답출력
		System.out.println(result);
		System.out.println(dataSet.classAttribute().value((int)result));
	}
}
