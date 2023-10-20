package wekaex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class SimpleWeka01 {

	public static void main(String[] args) throws Exception {
		int numfolds = 10;
		int numfold = 0;
		int seed = 1;
		
		Instances data = new Instances(
						 new BufferedReader(
						 new FileReader("c:/Temp/titanic_pre.arff")));
		
		// 데이터 분할 - 훈련 데이터, 평가 데이터
		Instances train = data.trainCV(numfolds, numfold, new Random(seed));
		Instances test = data.testCV(numfolds, numfold);
		
		// 정답 속성 인덱스 설정(마지막 속성)
		train.setClassIndex(train.numAttributes()-1);
		test.setClassIndex(test.numAttributes()-1);
				
		// 분류기 모델 설정
		//RandomForest mode = new RandomForest();
		DecisionTable model = new DecisionTable();
		
		// 평가 설정
		Evaluation eval = new Evaluation(train);
		
		eval.crossValidateModel(model, train, numfolds, new Random(seed));
		
		// RandomForest 훈련 실행
		model.buildClassifier(train);
		
		// 평가
		eval.evaluateModel(model, test);
		
		// 결과 출력
		System.out.println(model);
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toMatrixString());
		
	}
}
