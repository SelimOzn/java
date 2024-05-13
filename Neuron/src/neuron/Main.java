package neuron;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        double learningRate;
        int epok;
        ArrayList<Double> input = new ArrayList<>();
        String strClass;
        int[] target;
        String data;
        String [] spltData;
        NeuralNetwork neuralNetwork;
        double trueCount = 0.0;
        double dataCount = 0.0;
        double accuracyScore;
                
        learningRate = 0.025;
        epok = 100;

        Neuron n1 = new Neuron();
        Neuron n2 = new Neuron();
        Neuron n3 = new Neuron();
        
        n1.randWeights(3);
        n2.randWeights(3);
        n3.randWeights(3);
        neuralNetwork = new NeuralNetwork();
        neuralNetwork.addNeuron(n1);
        neuralNetwork.addNeuron(n2);
        neuralNetwork.addNeuron(n3);
        
        try {
            
            
            for (int k=0; k < epok; k++){
                File file = new File("C:\\Users\\iris.data");
                Scanner myReader = new Scanner(file);
                
                for (int m=0; m<150; m++){
                    data = myReader.nextLine();
                    spltData = data.split(",");
                    input = new ArrayList<>();
                    for (int i=0; i < 4; i++){
                        input.add(Double.parseDouble(spltData[i])/10.0);
                    }
                    
                    strClass = (spltData[4]);
                    
                    if (strClass.equals("Iris-setosa")){
                        target = new int[] {1,0,0};
                    }
                    else if(strClass.equals("Iris-versicolor")){
                        target = new int[]{0,1,0};
                    }else{
                        target = new int[]{0,0,1};
                    }
                    
                    neuralNetwork.getNetwork().get(0).setInputs(input);
                    neuralNetwork.getNetwork().get(1).setInputs(input);
                    neuralNetwork.getNetwork().get(2).setInputs(input);
                    neuralNetwork.getNetwork().get(0).multAdd();
                    neuralNetwork.getNetwork().get(1).multAdd();
                    neuralNetwork.getNetwork().get(2).multAdd();
                    neuralNetwork.outputvsTarget(target, learningRate);
                    
                    dataCount++;

                }
                
                file = new File("C:\\Users\\iris.data");
                myReader = new Scanner(file);
                
                for (int m=0; m<150; m++){
                    data = myReader.nextLine();
                    spltData = data.split(",");
                    input = new ArrayList<>();
                    for (int i=0; i < 4; i++){
                        input.add(Double.parseDouble(spltData[i]));
                    }
                    strClass = new String(spltData[4]);
                    if (strClass.equals("Iris-setosa")){
                        target = new int[] {1,0,0};
                    }
                    else if(strClass.equals("Iris-versicolor")){
                        target = new int[]{0,1,0};
                    }
                    else{
                        target = new int[]{0,0,1};
                    }
                    
                    neuralNetwork.getNetwork().get(0).setInputs(input);
                    neuralNetwork.getNetwork().get(1).setInputs(input);
                    neuralNetwork.getNetwork().get(2).setInputs(input);
                    neuralNetwork.getNetwork().get(0).multAdd();
                    neuralNetwork.getNetwork().get(1).multAdd();
                    neuralNetwork.getNetwork().get(2).multAdd();
                    
                    if (neuralNetwork.accuracyControl(target) == true){
                        trueCount++;
                    }
                }
            }
            accuracyScore = (trueCount / dataCount) * 100.0;
            System.out.println("Doğruluk değeri: " + accuracyScore);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        }
    }

