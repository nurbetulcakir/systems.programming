import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class P3_150120031_150121545_150120995_150119815 {
	private int numberSet;
    private int assc;
    private int blockSize;
    private int[] validBits;
    private int[] tagBits;
    private int hit;
    private int miss;
    private int eviction;

    public P3_150120031_150121545_150120995_150119815(int numberSet, int assc, int blockSize) {
        this.numberSet = numberSet;
        this.assc = assc;
        this.blockSize = blockSize;

        validBits = new int[numberSet * assc];
        tagBits = new int[numberSet * assc];

        hit = 0;
        miss = 0;
        eviction = 0;
    }
    
    private void executeMemoryAccess(int address) {
        int setIndex = (address >> blockSize) & (numberSet - 1);
        int tag = address >> (blockSize + (int) (Math.log(numberSet) / Math.log(2)));

        boolean hitBoolean = false;
        boolean emptyLineFound = false;
        int evictionLine = 0;

        for (int i = 0; i < assc; i++) {
            int cacheIndex = setIndex * assc + i;
            if (validBits[cacheIndex] == 1 && tagBits[cacheIndex] == tag) {
                hitBoolean = true;
                break;
            }
        }
        
        if (hitBoolean) {
            hit++;
            System.out.println("Hit");
        } else {
            miss++;
            System.out.println("Miss");

            // Check if there is an empty line in the set
            for (int i = 0; i < assc; i++) {
                int cacheIndex = setIndex * assc + i;
                if (validBits[cacheIndex] == 0) {
                    emptyLineFound = true;
                    evictionLine = i;
                    break;
                }
            }
            if (emptyLineFound) {
                validBits[setIndex * assc + evictionLine] = 1;
                tagBits[setIndex * assc + evictionLine] = tag;
                System.out.println("Place in set " + setIndex);
            } else {
                eviction++;
                evictionLine = (int) (Math.random() * assc);
                validBits[setIndex * assc + evictionLine] = 1;
                tagBits[setIndex * assc + evictionLine] = tag;
                System.out.println("Eviction");
                System.out.println("Store in set " + setIndex);
            }
        }
    }
    public CharSequence createCache(String traceFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(traceFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String operation = parts[0];
                int address = Integer.parseInt(parts[1], 16);
                System.out.println(operation + " " + address);
                
                if (operation.equals("L") || operation.equals("S")) {
                    executeMemoryAccess(address);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("hits:" + hit + " misses:" + miss + " evictions:" + eviction);
        for (int i = 0; i < numberSet; i++) {
            for (int j = 0; j < assc; j++) {
                int cacheIndex = i * assc + j;
                int tagBit = tagBits[cacheIndex];
                System.out.println(i + ", " + j + ", " + tagBit);
            }
        }
		return traceFile;
    }
    public static void main(String[] args) throws IOException {
        int s = 0;
        int E = 0;
        int b = 0;
        int S = 0;
        
        String fileName = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input file name: ");
        fileName = scanner.next();
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        
        int numberSet = (int) Math.pow(2, s);
        int blockSize = (int) Math.pow(2, b);
        
        
        P3_150120031_150121545_150120995_150119815 simulator = new P3_150120031_150121545_150120995_150119815(numberSet, E, blockSize);
        simulator.createCache(fileName);
        writer.append(simulator.createCache(fileName));
        
        
    }
}

