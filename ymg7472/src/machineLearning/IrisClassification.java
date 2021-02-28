package machineLearning; 


import org.apache.log4j.BasicConfigurator;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.common.io.ClassPathResource;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;

/**
 * <pre>
 * kr.co.swh.lecture.opensource.deepleaning4j 
 * IrisClassification.java
 *
 * 설명 :
 * 	Iris 꽃 분류 : https://deeplearning4j.konduit.ai/
 * 	개/고양이 인식 분류 Keras 기반 CNN 알고리즘: https://chealin93.tistory.com/69
 * 
 * 	자바 윤곽선 추출 : w
 * </pre>
 * 
 * @since : 2020. 11. 8.
 * @author : tobby48
 * @version : v1.0
 */
public class IrisClassification {

    private static final int FEATURES_COUNT = 4;
    private static final int CLASSES_COUNT = 3;

    public static void main(String[] args) {

        BasicConfigurator.configure();
        loadData();

    }

    private static void loadData() {
        try{
        	//	csv 파일을 Read
        	RecordReader recordReader = new CSVRecordReader(0,',');
            recordReader.initialize(new FileSplit(
            		//	class패스에 추가 되어있는 경로로부터 Read (Build Path에 추가되어 있는 경로)
                    new ClassPathResource("ml/iris.csv").getFile()
            ));

            //	데이터를 분류하는 기준이 되는 Feature 갯수
            //	데이터를 분류 갯수 (데이터에 분류를 위한 라벨링이 되어 있어야 함)
            //	특정 시드(날짜 정보를 통해 일정한 패턴을 가진 난수) 을 통해 데이터를 셔플
            DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, 150, FEATURES_COUNT, CLASSES_COUNT);
            DataSet allData = iterator.next();
            allData.shuffle(123);

            //	정규화(Normalization) : 머신러닝에서 Input되는 데이터는 최초 정규화를 거치게 된다.
            //	정규분포
            //	ex. 우리나라 여성 평균 외모 점수(만약 그런 것이 존재한다면)가 50점이라면, 당연히 50점 부근인 사람이 가장 많을 것이다. 80점 이상인 사람은 그만큼 적을 것이다. 
            //	그런데도 불구하고 어떻게든 80점 이상의 여자와 결혼하려고 한다면 그만큼 결혼확률이 낮아지는 것이다.
            //	종 모양
            //	사용 이유: 
            //		1. 표준화 된 입력을 통해 Gradient Descent 및 Bayesian estimation을 보다 편리하게 수행
            //		2. 0~1로 표준화
            //	Gradient Descent : 깊은 골짜기를 찾고 싶을 때에는 가장 가파른 내리막 방향으로 산을 내려가면 될 것. 미분을 통해 계산
            //	Bayesian estimation : 자신이 생각한 시나리오나 지식이 실제 정보를 통해 Update되고 설득되어지는 알고리즘.
            DataNormalization normalizer = new NormalizerStandardize();
            normalizer.fit(allData);
            normalizer.transform(allData);

            //	데이터 분할 : 교육용 65%(0.65)와 테스트 용 나머지 35%(0.35)
            SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65);
            DataSet trainingData = testAndTrain.getTrain();
            DataSet testingData = testAndTrain.getTest();

            irisNNetwork(trainingData, testingData);

        } catch (Exception e) {
            Thread.dumpStack();
            new Exception("Stack trace").printStackTrace();
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }

    private static void irisNNetwork(DataSet trainingData, DataSet testData) {

    	//	뉴런 신경망
    	//	https://miro.medium.com/max/625/1*VBRB-_ukJfaZ3HHN1CgJCg.png
    	//	https://youtu.be/bfmFfD2RIcg
        MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
                .activation(Activation.TANH)
                .weightInit(WeightInit.XAVIER)
                .updater(new Nesterovs(0.1, 0.9))
                .l2(0.0001)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(FEATURES_COUNT).nOut(3).build())
                .layer(1, new DenseLayer.Builder().nIn(3).nOut(3).build())
                .layer(2, new OutputLayer.Builder(
                        LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD).activation(Activation.SOFTMAX)
                        .nIn(3).nOut(CLASSES_COUNT).build())
//                .backprop(true).pretrain(false)
                .build();

        //	신경망 모델 생성 (훈련 데이터을 통해)
        MultiLayerNetwork model = new MultiLayerNetwork(configuration);
        model.init();
        model.fit(trainingData);

        //	훈련된 모델을 테스트 데이터를 통해 평가
        INDArray output = model.output(testData.getFeatures());
        Evaluation eval = new Evaluation(3);
        eval.eval(testData.getLabels(), output);
        System.out.println(eval.stats());

//        Accuracy:        정확도
//        Precision:       정밀도
//        Recall:          재현율
//        F1 Score:        테스트의 정밀도와 재현율에서 계산되는 정확도를 나타내는 척도
    }
}