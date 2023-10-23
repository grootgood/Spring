package wekaex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class WekaUtil {

	public static Instances makeInstances(String[] attrNames, String[] classLabels) {
		ArrayList<Attribute> attrList = new ArrayList<>();
		for(String name: attrNames) {
			attrList.add(new Attribute(name));
		}
		return makeInstances(attrList, classLabels);
	}
	
	public static Instances makeInstances(int length, String[] classLabels) {
		ArrayList<Attribute> attrList = new ArrayList<>();
		for(int i=0; i<length; i++) {
			attrList.add(new Attribute("attr_"+i));
		}
		return makeInstances(attrList, classLabels);
	}
	
	public static Instances makeInstances(ArrayList<Attribute> attrList, String[] classLabels) { // 정답 제외한 Attribute 리스트
		List<String> labelList = Arrays.asList(classLabels);
		attrList.add(new Attribute("@@class@@", labelList));
		
		Instances dataSet = new Instances("dataset", attrList, 0);
		dataSet.setClassIndex(attrList.size()-1); // 정답 인덱스로 지정
		return dataSet;
	}
}
