
package neuron;

import java.util.ArrayList;
import java.util.Arrays;

public class NeuralNetwork {
    
    private ArrayList<Neuron> network = new ArrayList<>();
    private int classLocation;
    private int maxLocation;
    private int theClass;
    private boolean isTrue;
        
    public void addNeuron(Neuron neuron){
        this.network.add(neuron);
    }
    
    public void adjustWeights(double learningRate,int location){
        getNetwork().get(location).newWeights(learningRate);
    }
    
    public void outputvsTarget(int[] target, double learningRate){
        theClass = findClass(target);
        
        if (this.accuracyControl(target) == false){
            this.adjustWeights(-learningRate, maxLocation);
            this.adjustWeights(learningRate, classLocation);
        }
    }
    
    public int findClass(int [] target){
        for (int i=0; i < target.length; i++){
            if (target[i] == 1){
                classLocation = i;
            }
        }
        return classLocation;
    }

    /**
     * @return the network
     */
    public ArrayList<Neuron> getNetwork() {
        return network;
    }

    /**
     * @param network the network to set
     */
    public void setNetwork(ArrayList<Neuron> network) {
        this.network = network;
    }
    
    public boolean getIsTrue(){
        return isTrue;
    }
    
    public boolean accuracyControl(int [] target){
        theClass = findClass(target);
        if (getNetwork().get(0).getOutput() > getNetwork().get(1).getOutput() && 
                getNetwork().get(0).getOutput() > getNetwork().get(2).getOutput()){
            maxLocation = 0;
            if (theClass == 0){
                isTrue = true;
            }
            else{
                isTrue = false;
            }
        }
        
        else if (getNetwork().get(1).getOutput() > getNetwork().get(2).getOutput()){
            maxLocation = 1;
            if (theClass == 1){
                isTrue = true;
            }
            else{
                isTrue = false;
            }
        }
            
        else{
            maxLocation = 2;
            if (theClass == 2){
                isTrue = true;
            }
            else{
                isTrue = false;
            }
        }
        return isTrue;
    }
}
    

