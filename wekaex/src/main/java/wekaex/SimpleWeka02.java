package wekaex;

import java.io.File;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public class SimpleWeka02 {

	public static void main(String[] args) throws Exception{
		int numfolds = 10;
		int numfold = 0;
		int seed = 1;
		
		CSVLoader csvloader = new CSVLoader();
		csvloader.setSource(new File("c:/Temp/basketball_stat2.csv"));
		Instances data = csvloader.getDataSet();
		Instances train = data.trainCV(numfolds, numfold, new Random(seed));
		Instances test = data.testCV(numfolds, numfold);
		
		RandomForest model = new RandomForest();
		
		train.setClassIndex(train.numAttributes()-1);
		test.setClassIndex(train.numAttributes()-1);
		
		Evaluation eval = new Evaluation(train);
		
		eval.crossValidateModel(model, train, numfolds, new Random(seed));
		
		model.buildClassifier(train);
		
		eval.evaluateModel(model, test);
		
		System.out.println(model);
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toMatrixString());
		
		// 실제 1개의 데이터 판정
		Instance indata = test.get(1);
		
		System.out.println(indata);
		
		// 예측(predict)
		double result = model.classifyInstance(indata);
		System.out.println(result);
		System.out.println(test.classAttribute().value((int)result));
	}
}
