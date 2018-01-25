package myio;
import mymath.*;
import java.io.*;

public class StringOps{
    
    public static String arrayToString(double[] arr){
	String result = "[";
	for (int i=0;i<arr.length-1;i++) result = result + arr[i] + ",";
	if (arr.length>0) result=result+arr[arr.length-1];
	result = result + "]";
	return result;
    }

    public static String arrayToString(double[] arr, int digits){ 
	String result = "[";
	for (int i=0;i<arr.length-1;i++) result = result + MyMathOps.roundDouble(arr[i],digits) + ",";
	if (arr.length>0) result=result+arr[arr.length-1];
	result = result + "]";
	return result;
    }

    public static DoubleVector stringToDoubleVector(String str){
	DoubleVector result = new DoubleVector();
	try{
	StreamTokenizer tokenzer = new StreamTokenizer(new StringReader(str));
	tokenzer.resetSyntax();
	tokenzer.wordChars(47,57); // 0-9
	tokenzer.wordChars(46,46); // .
	tokenzer.whitespaceChars(44,44); // ,
	tokenzer.whitespaceChars(91,91); // [
	tokenzer.whitespaceChars(93,93); // ]
	
	tokenzer.parseNumbers();
	
	double nextdouble;
	while (tokenzer.nextToken() != tokenzer.TT_EOF){
            nextdouble = tokenzer.nval;
	    //System.out.println(nextdouble);
	    result.add(nextdouble);
            }
	}
	catch (java.io.IOException e){System.out.println(e);};
        return result;
    }

    public static String arrayToString(int[] arr){
	String result = "[";
	for (int i=0;i<arr.length-1;i++) result = result + arr[i] + ",";
	if (arr.length>0) result=result+arr[arr.length-1];
	result = result + "]";
	return result;
    }


    public static String arrayToString(String[] arr){
	String result = "[";
	for (int i=0;i<arr.length-1;i++) result = result + arr[i] + ",";
	if (arr.length>0) result=result+arr[arr.length-1];
	result = result + "]";
	return result;
    }

    public static String matrixToString(int[][] mat){
	String result = "";
	for (int i=0;i<mat.length;i++)
	    result = result + arrayToString(mat[i]) + '\n';
	return result;
    }

    public static String matrixToString(double[][] mat){
	String result = "";
	for (int i=0;i<mat.length;i++)
	    result = result + arrayToString(mat[i]) + '\n';
	return result;
    }

    public static double[] sortArrayCopy(double[] ar){
	// mergesort. returns new array
	double[] result = new double[ar.length];
	if (ar.length > 1){
	int halflength = ar.length/2;
	double[] lar = new double[halflength];
	double[] rar = new double[ar.length-halflength];
	for (int i=0;i<halflength;i++){
	    lar[i]=ar[i];
	}
	for (int i=0;i<ar.length-halflength;i++){
	    rar[i]=ar[halflength+i];
	}
	double[] lsorted = sortArrayCopy(lar);
	double[] rsorted = sortArrayCopy(rar);
	int lc=0;
	int rc=0;
	for (int c=0;c<ar.length;c++){
	    if (rc==rar.length || (lc < lar.length && lar[lc]<=rar[rc])){
		result[c]=lar[lc];
		lc++;
	    }
	    else{
		result[c]=rar[rc];
		rc++;
	    }
	}
	}
	else{
	    result[0]=ar[0];
	}
	return result;
    }

    public static void sortArray(double[] ar){
	// mergesort. sorts the argument array
	if (ar.length > 1){
	    int halflength = ar.length/2;
	    double[] lar = new double[halflength];
	    double[] rar = new double[ar.length-halflength];
	    for (int i=0;i<halflength;i++){
		lar[i]=ar[i];
	    }
	    for (int i=0;i<ar.length-halflength;i++){
		rar[i]=ar[halflength+i];
	    }
	    sortArray(lar);
	    sortArray(rar);
	    int lc=0;
	    int rc=0;
	    for (int c=0;c<ar.length;c++){
		if (rc==rar.length || (lc < lar.length && lar[lc]<=rar[rc])){
		    ar[c]=lar[lc];
		    lc++;
		}
		else{
		    ar[c]=rar[rc];
		    rc++;
		}
	    }
	}
    }

    public static void sortArray(int[] ar){
	// mergesort. sorts the argument array
	if (ar.length > 1){
	    int halflength = ar.length/2;
	    int[] lar = new int[halflength];
	    int[] rar = new int[ar.length-halflength];
	    for (int i=0;i<halflength;i++){
		lar[i]=ar[i];
	    }
	    for (int i=0;i<ar.length-halflength;i++){
		rar[i]=ar[halflength+i];
	    }
	    sortArray(lar);
	    sortArray(rar);
	    int lc=0;
	    int rc=0;
	    for (int c=0;c<ar.length;c++){
		if (rc==rar.length || (lc < lar.length && lar[lc]<=rar[rc])){
		    ar[c]=lar[lc];
		    lc++;
		}
		else{
		    ar[c]=rar[rc];
		    rc++;
		}
	    }
	}
    }


    public static int[] indexToTuple(int ind, int dim, int range)
	/* returns the tuple i_0,i_2,...,i_(dim-1) 
	 * that occurs in place 'ind' in a 
	 * lexicographic enumeration of all 
	 * 'dim'-tuples of integers in the range 
	 * [0..range-1]
	 * 0 <= ind <= range^dim 
	 */
    {
        int [] result = new int[dim];
        for (int i=0; i<dim; i++)
	    {
		result[dim-1-i] = ind % range;
		ind = ind/range;
	    }
        
        return result;
    }


    public static int tupleToIndex(int[] tuple, int range)
	/* returns the index of 'tuple': i_0,i_2,...,i_(tuple.length-1)
	 * in an enumeration of all tuples over range [0..range-1]
	 * Error if i_j>range-1 for some j
	 */
    {
        int result = 0;
        int k = tuple.length;
        int factor = 1;
        
        
	for (int i = 0; i < k  ; i++)
            {
                result = result + (tuple[k-1-i])*factor;
                factor = factor * range;
            }
        
        return result;
    }

    public static double[] intArrayToDoubleArray(int[] intarray){
	double[] result = new double[intarray.length];
	for (int i=0;i<result.length;i++)
	    result[i]=(double)intarray[i];
	return result;
    }
    
    public static String repeat(String str, int times){
	
	String result = "";
	for (int i = 0; i < times; i += 1)
	    result += str;
	return result;
    }
}
