package test;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Exercises {
    public static void main(String[] args) {
        Exercises exercises=new Exercises();
        System.out.print("请输入你的题目数量：");
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        exercises.setCount(number);
        exercises.showArithmetic();
    }
    private int minNumber=0;
    private int maxNumber=100;
    private int count=0;
    private int countTrue=0;
    private String result="";
    private  float resultNumber=0;
    private char operations[]=new char[]{'+','-','*','/'};
    public void setCount(int count) {
        this.count = count;
    }
    private int getRandomNumber(){
       return  (int)(minNumber+Math.random()*(maxNumber-minNumber+1));
    }
    private char getRandomOperation(){
        int random=(int)(Math.random()*4);
        return operations[random];
    }
    private float getOperationResult(char operation,float x,float y){
        float result=0;
        switch (operation){
            case '+':
                result=x+y;
                break;
            case '-':
                result=x-y;
                break;
            case '*':
                result=x*y;
                break;
            case '/':
                result=x/y;
                break;
        }
        return result;
    }
    private void  arithmetic(){
        int number[]=new int[3]; //运算数量
        char []operation=new char[2]; //符号数量
        while (true){
           for(int i=0;i<number.length;i++){
               number[i]=getRandomNumber(); //给变量赋值
           }
           operation[0]=getRandomOperation(); //给符号位赋值
           operation[1]=getRandomOperation();
           if(operation[0]=='*' || operation[0]=='/'){ //判断第一个是否为*，/ 是，则直接计算
              if(number[1]==0)continue;
               resultNumber=getOperationResult(operation[0],number[0],number[1]);
               if(operation[1]=='/' && number[2]==0)continue;
               resultNumber=getOperationResult(operation[1],resultNumber,number[2]);
          }else {
               //如果第一个符号位不是* ，/那就先算第二个数和三个数的值后，再与第一个值计算
              if(operation[1]=='/'&& number[2]==0)continue;
              if(operation[0]=='-'){
                  resultNumber=getOperationResult(operation[1],-number[1],number[2]);
                  operation[0]='+';
              }
              else{
                  resultNumber=getOperationResult(operation[1],number[1],number[2]);
              }
               resultNumber=getOperationResult(operation[0],number[0],resultNumber);
          }
          if(resultNumber<0)continue;
          else break;
       }
        //              将数字和符号串联起来 作为输出题目的字符
        result=number[0]+""+operation[0]+number[1]+operation[1]+number[2];
       System.out.print(result+"=");
        Scanner sc = new Scanner(System.in);
        float age = sc.nextFloat();
//        当用户输入的数字和正确答案相比的精确度大于0.01时，判断用户计算错误
        if(Math.abs(age-resultNumber)>0.01){
            System.out.print("你计算错误，正确的答案为："+String.format("%.2f", resultNumber)+"\n");
        }else{
            System.out.print("你计算正确\n");
            countTrue++;
        }
    }
    public void showArithmetic(){
        int countExercise=count;
        while (countExercise>0){
            countExercise--;
            arithmetic();
        }
        System.out.print("你的正确率为"+(float)countTrue/count+"%");
    }

}
