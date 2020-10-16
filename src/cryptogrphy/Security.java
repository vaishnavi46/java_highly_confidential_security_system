package cryptogrphy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Security 
{
	public static String encryption(String input,String key)
	{
		String Inputresult = convertStringToBinary(input);
		//System.out.println(Inputresult);
		String keyresult = convertStringToBinary(key);
		//System.out.println(keyresult);
		StringBuffer sb = new StringBuffer();
      for (int i = 0; i < Inputresult.length(); i++) 
      {
         sb.append(Inputresult.charAt(i)^keyresult.charAt(i));
      }
	  String ciphertext;
      ciphertext=sb.toString();
      return ciphertext;
	}
	
	public static String decryption(String input,String key)
	{
		String decrypt;
        key=convertStringToBinary(key);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) 
        {
           sb.append(input.charAt(i)^key.charAt(i));
        }
        String plaintext;
        plaintext=sb.toString();
        decrypt=prettyBinary(plaintext, 8, " ");
        decrypt=BinarytoString(decrypt);
        return decrypt;
	}
	
	
		public static String BinarytoString(String input )
		{
			 String raw = Arrays.stream(input.split(" "))
		                .map(binary -> Integer.parseInt(binary, 2))
		                .map(Character::toString)
		                .collect(Collectors.joining()); 

		       return raw;
		}
		
		public static String convertStringToBinary(String input) {

	        StringBuilder result = new StringBuilder();
	        
	        char[] chars = input.toCharArray();
	        //System.out.println(chars[0]);
	        for (char aChar : chars) {
	            result.append(
	                    String.format("%8s", Integer.toBinaryString(aChar))   
	                            .replaceAll(" ", "0")                         
	            );
	        }
	        return result.toString();

	    }
		 public static String prettyBinary(String binary, int blockSize, String separator) {

		        List<String> result = new ArrayList<>();
		        int index = 0;
		        while (index < binary.length()) {
		            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
		            index += blockSize;
		            
		        }
		        return result.stream().collect(Collectors.joining(separator));
		    }
		

}
