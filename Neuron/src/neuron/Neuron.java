package neuron;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {
    
    private ArrayList<Double> inputs;
    private ArrayList<Double> weights;
    private Random randomDouble = new Random();
    private double output;
    private ArrayList<Double> newWeights;
    private Random r;

    public void Neuron(){
        inputs = new ArrayList<>();
        weights = new ArrayList<>();
    }
    
    public void randWeights(int inputLength){
        Random r = new Random();
        weights = new ArrayList<>();
        for (int i=0; i < inputLength; i++){
            weights.add(r.nextDouble());
        }
    }
    public void Neuron(ArrayList<Double> input){
        this.setInputs(input);
    }
    public void Neuron(ArrayList<Double> input, ArrayList<Double> weight){
        this.setInputs(input);
        this.setWeights(weight);
    }
    public void multAdd(){
        output = 0.0;
        for (int i=0; i < (this.getInputs().size()-1); i++){
            setOutput(getOutput() + getInputs().get(i) * getWeights().get(i));
        }
    }
    public void newWeights(double learningRate){
        newWeights = new ArrayList<>();
        for (int i=0; i<this.weights.size(); i++){
            newWeights.add(this.weights.get(i) + (learningRate * this.inputs.get(i)));
        }
        this.setWeights(newWeights);
    }
    /**
     * @return the inputs
     */
    public ArrayList<Double> getInputs() {
        return inputs;
    }
    /**
     * @param inputs the inputs to set
     */
    public void setInputs(ArrayList<Double> inputs) {
        this.inputs = inputs;
    }
    /**
     * @return the weights
     */
    public ArrayList<Double> getWeights() {
        return weights;
    }
    /**
     * @param weights the weights to set
     */
    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }
    /**
     * @return the output
     */
    public double getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(double output) {
        this.output = output;
    }
}
