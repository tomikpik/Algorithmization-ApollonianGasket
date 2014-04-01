/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Tom
 */
public class Main {
    private double D1,D2,minD,maxD,maxCurvature;
    private double surface,perimeter;
    
    public static void main(String[] args) throws IOException {
        Main ag = new Main();
        
       // ag.runTests();
        ag.readInput();
        ag.askQuidoToStartComputing();
        System.out.println(ag.getResult());
    }
    

    public void askQuidoToStartComputing(){
        maxCurvature = maxD/minD;
        double cD1=maxD/D1;
        double cD2=maxD/D2;
        double cD3=-1;
        
     
        
        
        this.quidoComputes(cD1,cD2,cD3);
        if(D1>=minD){
            surface+=surface(cD1);
            perimeter+=perimeter(cD1);
        }
        if(D2>=minD){
            surface+=surface(cD2);
            perimeter+=perimeter(cD2);
        }

        
        
    }
    
    public void quidoComputes(double D1,double D2,double D3){
        double D4 = this.descartesPlus(D1, D2, D3);
        double D5 = this.descartesMinus(D1, D2, D3); 
        
        if((D4<=maxCurvature)&&(D4>=Math.max(Math.max(D1,D2),D3))){
            surface+=surface(D4);
            perimeter+=perimeter(D4);
            this.quidoComputes(D4, D2, D3);
            this.quidoComputes(D1, D4, D3);
            this.quidoComputes(D1, D2, D4);
        }
        if((D5<=maxCurvature)&&(D5>=Math.max(Math.max(D1,D2),D3))){
            surface+=surface(D5);
            perimeter+=perimeter(D5);
            this.quidoComputes(D5, D2, D3);
            this.quidoComputes(D1, D5, D3);
            this.quidoComputes(D1, D2, D5);
            
        }
        
    }
       
    public double descartesPlus(double a, double b, double c){

        Double p3 = 2*Math.sqrt(b*(a+c)+(c*a));
        if(Double.isNaN(p3)){
            return a+b+c;
        } else {
            return (a+b+c)+p3;
        }
    }
    
    public double descartesMinus(double a, double b, double c){
        Double p3 = 2*Math.sqrt(b*(a+c)+(c*a));
        if(Double.isNaN(p3)){
            return a+b+c;
        } else {
            return (a+b+c)-p3;
        }
    }
    
    public double surface(double curvature){
        return Math.PI*Math.pow((maxD/(curvature*2)),2);
    }
    
    public double perimeter(double curvature){
        return Math.PI*maxD/curvature;
    }
    
    
    
    
    
    
    
    
    
    
    public String getResult(){
        double s = Math.round(surface*1000000)/1000000.0d;
        double p = Math.round(perimeter*1000000)/1000000.0d;
        return String.format("%f %f",s,p); 
    }
    
    public void readInput() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        D1=Double.valueOf(tokenizer.nextToken());
        D2=Double.valueOf(tokenizer.nextToken());
        minD=Double.valueOf(tokenizer.nextToken());
        maxD=D1+D2;
        
    }
    
    public void readInputTests(String input) throws IOException{
        StringTokenizer tokenizer = new StringTokenizer(input);
        D1=Double.valueOf(tokenizer.nextToken());
        D2=Double.valueOf(tokenizer.nextToken());
        minD=Double.valueOf(tokenizer.nextToken());
        maxD=D1+D2;
        surface=0;
        perimeter=0;
    }
    
    
    public void printInput(){
        System.out.println(D1+" "+D2+" "+minD);
    }
    
    public void runTests() throws IOException{
        String[][] tests = {{"17.000000 40.000000 1.000000"   ,"2439,258588 835,263228"},
                            {"10.000000 10.000000 2.000000"   ,"261,799388 146,607657"},
                            {"2.000000 50.000000 1.000000"    ,"2066,462477 408,747226"},
                            {"1000.022000 811.047000 0.025000","2575261,696138 292760,963694"}};
        
        for(int i=0;i<4;i++){
            System.out.println("\nTest "+(i+1)+" -----------------");
            System.out.println("I:  "+tests[i][0]);
            
            this.readInputTests(tests[i][0]);
            this.askQuidoToStartComputing();
            
            String realResult = this.getResult();
            System.out.println("DR: "+tests[i][1]);
            System.out.println("RR: "+realResult);
            if(realResult.equals(tests[i][1])){
                System.out.println("PASSED");
            } else {
                System.out.println("Failed");
            }
            
        }
        
        
    }
    
}
