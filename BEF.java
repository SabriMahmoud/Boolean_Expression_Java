/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetalgo;

import java.util.ArrayList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author lord of music
 */
public class BEF {
    
    
    public static String evaluateExpression(String expression) throws ScriptException{
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        String userVar[] = {"V = true", "F = false"};
        
        for (String k : userVar) {
                engine.eval(k);
        } 
        if(engine.eval(expression).toString().equals("1")||engine.eval(expression).toString().equals("true"))
        { 
            return("V");
        }
        else return("F");
            
        }
    public static String [] evaluateSplitedByOr(String [] splitedByOr) throws ScriptException{
        String endOperation="";
        String startOperation="" ;
        String result;
        for(int j=0; j<splitedByOr.length;j++){
                    String miniExpression=splitedByOr[j];
                    
                    if(miniExpression.length()>=2){
                        
                        int len=miniExpression.length();
                        
                        if(miniExpression.charAt(0)=='&'){
                            startOperation+="&";
                            
                            miniExpression=miniExpression.substring(1,len);
                            len--;
                            
                        }
                   
                        if(miniExpression.charAt(len-1)=='&'){
                          
                            endOperation+="&";                            
                            miniExpression=miniExpression.substring(0,len-1);
                            
                             
                        }
                        else if(miniExpression.charAt(len-1)=='!')
                        {
                            endOperation+="&!";

                            miniExpression=miniExpression.substring(0,len-1);
                            
                            
                        }
                        
                        result=evaluateExpression(miniExpression);
                        result+=endOperation;
                        startOperation+=result;
                        
                        
                        splitedByOr[j]=startOperation;
                        endOperation="" ;
                        startOperation="";

                    }
                    }
        return(splitedByOr);
        }
    
        public static void main(String[] args) throws ScriptException {
            
        java.util.Scanner s=new java.util.Scanner(System.in);
        String inputs;
       
        
       
        
        //do{
            do
            {
     
                 System.out.println("Enter Expression ");
                 inputs=s.nextLine();
                
            }while(inputs.length()>100);
            
            
            
       // }while(i<20 && !inputs.equals(""));
            ArrayList<String> list=new ArrayList<String>();
            String expressions="";
            
            StringBuffer buffer=new StringBuffer(inputs);
            int i=0;
            String extra="";
            //cleaning the expression from ( and ) 
            while(i<buffer.length()){
                if(buffer.charAt(i)!='(' && buffer.charAt(i)!=')'){
                    expressions+=buffer.charAt(i);
                    i++;
                    
                }
                else {
                
                    if(!expressions.equals("") && expressions.length()!=1) {
                        list.add(expressions); 
                        extra="";
                    }
                    else extra=expressions;
                    buffer.delete(0, i+1);
                    i=0;
                    expressions="";
                    expressions+=extra;
                
                
                }
                
            }
            
            
            System.out.println(list);
            String [] splitedByOr;
            
            for(i=0;i<list.size();i++){
                String element=list.get(i);
                while(element.indexOf("|")!=-1){
                    element=element.replace('|',',');
                    
                }
                splitedByOr=element.split(",");
                splitedByOr=evaluateSplitedByOr(splitedByOr);
                String splitedResult=splitedByOr[0];
                for(int j=1;j<splitedByOr.length;j++){
                    
                    splitedResult+="|";
                    splitedResult+=splitedByOr[j];
                
                }
                
                
                list.set(i,splitedResult);
               
            }  
            
                        
            String lastResult="" ;
            for(String e:list){
                lastResult+=e;
            }
            System.out.println(lastResult);
            System.out.println("Expression :"+evaluateExpression(lastResult));
        
        //!V|V&V&!F&(F|V)&(!F|F|!V&V)
    
            }
}
