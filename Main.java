import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
	
	private ArrayList<Iris> trainingData = new ArrayList<Iris>();
	private ArrayList<Iris> testData = new ArrayList<Iris>();
	
	int k;
	
	
	public Main(String trainingData, String testData, int k){
		readData(trainingData, 0);
		readData(testData, 1);
		this.k = k;
		
		classify();
	}
	
	public void classify(){
		double[] distances;
	
		for(Iris i : testData){
			distances = calculateDistances(i);
			if(k == 1){
			int index = getNearestNeighbour(distances);
			setClassification(i, index);
			}
			else
				getNearestNeighbours(distances);
		}
		
		for(Iris i : testData){
			i.printResults();
		}
		
	}
	
	private void setClassification(Iris i, int index) {
		String classification = trainingData.get(index).getLabel();
		i.setClassification(classification);		
	}

	public int getNearestNeighbour(double[] distances){
		double lowest = distances[0];
		int index = 0;
		for(int i = 1; i < distances.length; i++){
			if(distances[i] < lowest){
				lowest = distances[i];
				index = i;
			}
		}
		return index;
	}
	
	public void getNearestNeighbours(double[] distances){
		
	}
	
	/**
	 * Calculates the Euclidean distance between the Iris parameter test and every training Iris in the training data.
	 * @param test
	 * @return
	 */
	public double[] calculateDistances(Iris test){
		double[] distances = new double[trainingData.size()];
		for(int i = 0; i < distances.length; i++){
				distances[i] = test.getEuclideanDistance(trainingData.get(i));
			}
		return distances;
	}
	
	/**
	 * Reads data from a file and creates the training and test collections of irises.
	 * 
	 * @param filename
	 * @param i (0 for training set, 1 for test set)
	 */
	public void readData(String filename, int i){
		try{
			Scanner sc = new Scanner(new File(filename));
			while(sc.hasNext()){
				double sepalL = sc.nextDouble();
				double sepalW = sc.nextDouble();
				double petalL = sc.nextDouble();
				double petalW = sc.nextDouble();
				String label = sc.next();
				
				if(i == 0)
					trainingData.add(new Iris(sepalL, sepalW, petalL, petalW, label));	
				else if(i == 1)
					testData.add(new Iris(sepalL, sepalW, petalL, petalW, label));
			}
			sc.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	
	
	public static void main(String[] args){
		try {
			int k = Integer.parseInt(args[2]);	//Parse string k-value to an integer
			new Main(args[0], args[1], k);
         } catch (NumberFormatException e) {
            System.err.println("Failed trying to parse a non-numeric argument, " + args[2]);
         }		
	}
}
