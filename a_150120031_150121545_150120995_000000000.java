import java.util.*;
import java.io.*;

public class a_150120031_150121545_150120995_000000000 {
	
	static String hexToBinary(String s) {
		
		String binaryString = "";
		
		for (int i =0; i < s.length(); i++) {
		switch(s.charAt(i)) {
		case '0': 
			binaryString += "0000";
			break;
		case '1': 
			binaryString += "0001";
			break;
		case '2':
			binaryString += "0010";
			break;	
		case '3':
			binaryString += "0011";
			break;
		case '4':
			binaryString += "0100";
			break;
		case '5':
			binaryString += "0101";
			break;
		case '6':
			binaryString += "0110";
			break;
		case '7':
			binaryString += "0111";
			break;
		case '8':
			binaryString += "1000";
			break;
		case '9':
			binaryString += "1001";
			break;
		case 'a':
			binaryString += "1010";
			break;
		case 'b':
			binaryString += "1011";
			break;
		case 'c':
			binaryString += "1100";
			break;
		case 'd':
			binaryString += "1101";
			break;
		case 'e':
			binaryString += "1110";
			break;
		case 'f':
			binaryString += "1111";
			break;
	
		}
	}
		
		
		
		return binaryString;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, NumberFormatException  {
		File hex_file = new File(("hex.txt").trim());
		Scanner hex_scanner = new Scanner(hex_file);
		String inputFileName;
		
		PrintStream out = new PrintStream(new File("output.txt"));
		PrintStream console = System.out;
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Byte ordering: ");
		String byteOrdering = input.nextLine();
		System.out.println("Data type: ");
		String dataType = input.nextLine();
		System.out.println("Data type size: ");
		int size = input.nextInt();
		
		System.setOut(console);
		
		System.setOut(out);
		
		int byteOrdNumber = 0;
		int dataTypeNumber = 0;
		
		if (byteOrdering.equals("Little Endian") || byteOrdering.equals("1")) {
			byteOrdNumber = 1;
		}
		else if (byteOrdering.equals("Big Endian") || byteOrdering.equals("b")) {
			byteOrdNumber = 2;
		}
		
		
		
		if (dataType.equals("Signed integer") || dataType.equals("int")) {
			dataTypeNumber = 1;
		}
		else if (dataType.equals("Unsigned integer") || dataType.equals("unsigned")) {
			dataTypeNumber = 2;
		}
		else if (dataType.equals("Floating point") || dataType.equals("float")) {
			dataTypeNumber = 3;
		}
		

		
		while(hex_scanner.hasNextLine()) {
			String[] currentLine = new String[12];
			inputFileName = hex_scanner.nextLine();
			inputFileName = inputFileName.replaceAll(" ", "");
			for(int i = 0; i < 12; i++) {
				currentLine[i] = "" + inputFileName.charAt(2*i) + inputFileName.charAt(2*i + 1);
			}
			String hexPiece = "";
			String[] hexPieceArray = new String[12/size];
			String binaryStr = "";
			
			switch(size) {
			
			case 1: 
				for(int i = 0; i < 12; i++)  {
					hexPiece = currentLine[i] + "";
					hexPieceArray[i] = hexPiece;
					binaryStr = hexToBinary(hexPieceArray[i]);
					if (dataTypeNumber == 1) {
						 if (binaryStr.substring(0,1).equals("1")) {
						String modifiedString = "";
						for (int j = 0; j < binaryStr.length(); j++) {
						     if (binaryStr.charAt(j) == '0') {
						         modifiedString += '1';
						     } else if (binaryStr.charAt(j) == '1') {
						         modifiedString += '0';
						     } else {
						         modifiedString += binaryStr.charAt(j);
						     }
						 }
						long num = Long.parseLong(modifiedString, 2);
						num++;
						String newString = Long.toBinaryString(num);
						System.out.format("%5f\n", -1*Long.parseLong(newString,2));
						 }
						 else {
							
							 System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
						 }
							 
					}
					else if (dataTypeNumber == 2) {
						
						System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
						}
					else if (dataTypeNumber == 3) {
						String sign = binaryStr.substring(0, 1);
				         String exponent1 = binaryStr.substring(1, 5);
				         String fraction = binaryStr.substring(5,8);
				        
				         double frac = 0.0;
				         for (int j = 0; j < fraction.length(); j++) {
				             if (fraction.charAt(j) == '1') {
				                 frac += 1.0 / Math.pow(2, j + 1);
				             }
				         }
				         int exp = 0;
				         if(Integer.parseInt(exponent1, 2)==0) {
				        	 
				        	 exp = (int) -(Integer.parseInt(exponent1, 2)+ Math.pow(2, exponent1.length()-1)-2) ;
				         }
				         else {
				        	 frac+=1;
				        	 exp = (int) (Integer.parseInt(exponent1, 2) - (Math.pow(2, exponent1.length()-1)-1)) ;
				         }
				         
				         double result = (sign.equals("1") ? -1 : 1) * (frac) * Math.pow(2, exp);
				         System.out.print(result +" ");
					}
					hexPiece = "";
				}
				
				break;
			case 2:
				for(int i = 0; i < 6; i++)  {
					hexPiece = currentLine[2*i] + "";
					hexPiece += currentLine[2*i+1] + "";
					//System.out.println(hexPiece);
					hexPieceArray[i] = hexPiece;
					binaryStr = hexToBinary(hexPieceArray[i]);
					if (dataTypeNumber == 1) {
						 if (binaryStr.substring(0,1).equals("1")) {
						String modifiedString = "";
						for (int j = 0; j < binaryStr.length(); j++) {
						     if (binaryStr.charAt(j) == '0') {
						         modifiedString += '1';
						     } else if (binaryStr.charAt(j) == '1') {
						         modifiedString += '0';
						     } else {
						         modifiedString += binaryStr.charAt(j);
						     }
						 }
						long num = Long.parseLong(modifiedString, 2);
						num++;
						String newString = Long.toBinaryString(num);
						
						System.out.format("%5f\n", -1*Long.parseLong(newString,2));
						 }
						 else {
							 
							 
						 }
							 
					}
					else if (dataTypeNumber == 2) {
						System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
						
						}
					else if (dataTypeNumber == 3) {
						String sign = binaryStr.substring(0, 1);
				         String exponent1 = binaryStr.substring(1, 6);
				         String fraction = binaryStr.substring(6,16);
				    
				         double frac = 0.0;
				         for (int j = 0; j < fraction.length(); j++) {
				             if (fraction.charAt(j) == '1') {
				                 frac += 1.0 / Math.pow(2, j + 1);
				             }
				         }
				         int exp = 0;
				         if(Integer.parseInt(exponent1, 2)==0) {
				        	 
				        	 exp = (int) -(Integer.parseInt(exponent1, 2)+ Math.pow(2, exponent1.length()-1)-2) ;
				         }
				         else {
				        	 frac+=1;
				        	 exp = (int) (Integer.parseInt(exponent1, 2) - (Math.pow(2, exponent1.length()-1)-1)) ;
				         }
				         
				         double result = (sign.equals("1") ? -1 : 1) * (frac) * Math.pow(2, exp);
				         System.out.print(result +" ");
					}
				       
					hexPiece = "";
				}
				
				break;	
			case 3:
				for(int i = 0; i < 4; i++)  {
					hexPiece = currentLine[3*i] + "";
					hexPiece += currentLine[3*i+1] + "";	
					hexPiece += currentLine[3*i+2] + "";	
					hexPieceArray[i] = hexPiece;
					binaryStr = hexToBinary(hexPieceArray[i]);
					if (dataTypeNumber == 1) {
						 if (binaryStr.substring(0,1).equals("1")) {
						String modifiedString = "";
						for (int j = 0; j < binaryStr.length(); j++) {
						     if (binaryStr.charAt(j) == '0') {
						         modifiedString += '1';
						     } else if (binaryStr.charAt(j) == '1') {
						         modifiedString += '0';
						     } else {
						         modifiedString += binaryStr.charAt(j);
						     }
						 }
						long num = Long.parseLong(modifiedString, 2);
						num++;
						String newString = Long.toBinaryString(num);
						
						System.out.format("%5f\n", -1*Long.parseLong(newString,2));
						 }
						 else {
							 
							 System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
						 }	 
					}
					else if (dataTypeNumber == 2) {
						System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
						}
					else if (dataTypeNumber == 3) {
						String sign = binaryStr.substring(0, 1);
				         String exponent1 = binaryStr.substring(1, 9);
				         String fraction = binaryStr.substring(9,21);
				         
				         
				         double frac = 0.0;
				         for (int j = 0; j < fraction.length(); j++) {
				             if (fraction.charAt(j) == '1') {
				                 frac += 1.0 / Math.pow(2, j + 1);
				             }
				         }
				         int exp = 0;
				         if(Integer.parseInt(exponent1, 2)==0) {
				        	 
				        	 exp = (int) -(Integer.parseInt(exponent1, 2)+ Math.pow(2, exponent1.length()-1)-2) ;
				         }
				         else {
				        	 frac+=1;
				        	 exp = (int) (Integer.parseInt(exponent1, 2) - (Math.pow(2, exponent1.length()-1)-1)) ;
				         }
				         
				         double result = (sign.equals("1") ? -1 : 1) * (frac) * Math.pow(2, exp);
				         System.out.print(result +" ");
					}
					hexPiece = "";
				}
				
				break;
			case 4:
				for(int i = 0; i < 3; i++)  {
					hexPiece = currentLine[4*i] + "";
					hexPiece += currentLine[4*i+1] + "";	
					hexPiece += currentLine[4*i+2] + "";	
					hexPiece += currentLine[4*i+3] + "";
					hexPieceArray[i] = hexPiece;
					binaryStr = hexToBinary(hexPieceArray[i]);
					if (dataTypeNumber == 1) {
						 if (binaryStr.substring(0,1).equals("1")) {
						String modifiedString = "";
						for (int j = 0; j < binaryStr.length(); j++) {
						     if (binaryStr.charAt(j) == '0') {
						         modifiedString += '1';
						     } else if (binaryStr.charAt(j) == '1') {
						         modifiedString += '0';
						     } else {
						         modifiedString += binaryStr.charAt(j);
						     }
						 }
						long num = Long.parseLong(modifiedString, 2);
						num++;
						String newString = Long.toBinaryString(num);
						
						System.out.format("%5f\n", -1*Long.parseLong(newString,2));
						 }
						 else {
							 
							 System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
						 }
							 
					}
					else if (dataTypeNumber == 2) {
						System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
						}
					else if (dataTypeNumber == 3) {
						String sign = binaryStr.substring(0, 1);
				         String exponent1 = binaryStr.substring(1, 11);
				         String fraction = binaryStr.substring(11,23);
				         
				         
				         double frac = 0.0;
				         for (int j = 0; j < fraction.length(); j++) {
				             if (fraction.charAt(j) == '1') {
				                 frac += 1.0 / Math.pow(2, j + 1);
				             }
				         }
				         
				         int exp = 0;
				         if(Integer.parseInt(exponent1, 2)==0) {
				        	 
				        	 exp = (int) -(Integer.parseInt(exponent1, 2)+ Math.pow(2, exponent1.length()-1)-2) ;
				         }
				         else {
				        	 frac+=1;
				        	 exp = (int) (Integer.parseInt(exponent1, 2) - (Math.pow(2, exponent1.length()-1)-1)) ;
				         }
				         
				         double result = (sign.equals("1") ? -1 : 1) * (frac) * Math.pow(2, exp);
				         System.out.print(result +" ");
					}
					hexPiece = "";
				}
				break;
			}
			
			// re ordering
			if (byteOrdNumber == 1) {
				switch(size) {
				
				case 1: 
					for(int i = 0; i < 12; i++)  {
						hexPiece = currentLine[i] + "";
						hexPieceArray[i] = hexPiece;
						binaryStr = hexToBinary(hexPieceArray[i]);
						if (dataTypeNumber == 1) {
							 if (binaryStr.substring(0,1).equals("1")) {
							String modifiedString = "";
							for (int j = 0; j < binaryStr.length(); j++) {
							     if (binaryStr.charAt(j) == '0') {
							         modifiedString += '1';
							     } else if (binaryStr.charAt(j) == '1') {
							         modifiedString += '0';
							     } else {
							         modifiedString += binaryStr.charAt(j);
							     }
							 }
							long num = Long.parseLong(modifiedString, 2);
							num++;
							String newString = Long.toBinaryString(num);
							
							System.out.format("%5f\n", -1*Long.parseLong(newString,2));
							 }
							 else {
								
								 System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
							 }	 
						}
						else if (dataTypeNumber == 2) {
							System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
							}
						else if (dataTypeNumber == 3) {
							String sign = binaryStr.substring(0, 1);
					         String exponent1 = binaryStr.substring(1, 5);
					         String fraction = binaryStr.substring(5,8);
					         
					        
					         double frac = 0.0;
					         for (int j = 0; j < fraction.length(); j++) {
					             if (fraction.charAt(j) == '1') {
					                 frac += 1.0 / Math.pow(2, j + 1);
					             }
					         }
					         int exp = 0;
					         if(Integer.parseInt(exponent1, 2)==0) {
					        	 
					        	 exp = (int) -(Integer.parseInt(exponent1, 2)+ Math.pow(2, exponent1.length()-1)-2) ;
					         }
					         else {
					        	 frac+=1;
					        	 exp = (int) (Integer.parseInt(exponent1, 2) - (Math.pow(2, exponent1.length()-1)-1)) ;
					         }
					         
					         double result = (sign.equals("1") ? -1 : 1) * (frac) * Math.pow(2, exp);
					         System.out.print(result +" ");
						}
						hexPiece = "";
					}
					break;
				case 2:
					for(int i = 0; i < 6; i++)  {
						hexPiece = currentLine[2*i+1] + "";
						hexPiece += currentLine[2*i] + "";
						hexPieceArray[i] = hexPiece;
						binaryStr = hexToBinary(hexPieceArray[i]);
						if (dataTypeNumber == 1) {
							 if (binaryStr.substring(0,1).equals("1")) {
							String modifiedString = "";
							for (int j = 0; j < binaryStr.length(); j++) {
							     if (binaryStr.charAt(j) == '0') {
							         modifiedString += '1';
							     } else if (binaryStr.charAt(j) == '1') {
							         modifiedString += '0';
							     } else {
							         modifiedString += binaryStr.charAt(j);
							     }
							 }
							long num = Long.parseLong(modifiedString, 2);
							num++;
							String newString = Long.toBinaryString(num);
							
							System.out.format("%5f\n", -1*Long.parseLong(newString,2));
							 }
							 else {
								 System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
							 }	 
						}
						else if (dataTypeNumber == 2) {
							System.out.format("%5f\n", Long.parseLong(binaryStr, 2));
							}
						else if (dataTypeNumber == 3) {
							String sign = binaryStr.substring(0, 1);
					         String exponent1 = binaryStr.substring(1, 6);
					         String fraction = binaryStr.substring(6,16);
					        
					         double frac = 0.0;
					         for (int j = 0; j < fraction.length(); j++) {
					             if (fraction.charAt(j) == '1') {
					                 frac += 1.0 / Math.pow(2, j + 1);
					             }
					         }
					         int exp = 0;
					         if(Integer.parseInt(exponent1, 2)==0) {
					        	 
					        	 exp = (int) -(Integer.parseInt(exponent1, 2)+ Math.pow(2, exponent1.length()-1)-2) ;
					         }
					         else {
					        	 frac+=1;
					        	 exp = (int) (Integer.parseInt(exponent1, 2) - (Math.pow(2, exponent1.length()-1)-1)) ;
					         }
					         
					         double result = (sign.equals("1") ? -1 : 1) * (frac) * Math.pow(2, exp);
					         System.out.print(result +" ");
						}
						hexPiece = "";
					}
					
					
					break;	
				case 3:
					for(int i = 0; i < 4; i++)  {
						hexPiece = currentLine[3*i+2] + "";
						hexPiece += currentLine[3*i+1] + "";
						hexPiece += currentLine[3*i] + "";	
						hexPieceArray[i] = hexPiece;
						binaryStr = hexToBinary(hexPieceArray[i]);
						if (dataTypeNumber == 1) {
							 if (binaryStr.substring(0,1).equals("1")) {
							String modifiedString = "";
							for (int j = 0; j < binaryStr.length(); j++) {
							     if (binaryStr.charAt(j) == '0') {
							         modifiedString += '1';
							     } else if (binaryStr.charAt(j) == '1') {
							         modifiedString += '0';
							     } else {
							         modifiedString += binaryStr.charAt(j);
							     }
							 }
							long num = Long.parseLong(modifiedString, 2);
							num++;
							String newString = Long.toBinaryString(num);
							System.out.println(-1*Long.parseLong(newString,2));
							 }
							 else {
								 System.out.println(Long.parseLong(binaryStr, 2));
								 
							 }	 
						}
						else if (dataTypeNumber == 2) {
							System.out.println(Long.parseLong(binaryStr, 2));
							}
						else if (dataTypeNumber == 3) {
							String sign = binaryStr.substring(0, 1);
					         String exponent1 = binaryStr.substring(1, 9);
					         String fraction = binaryStr.substring(9,21);
					         
					        
					         double frac = 0.0;
					         for (int j = 0; j < fraction.length(); j++) {
					             if (fraction.charAt(j) == '1') {
					                 frac += 1.0 / Math.pow(2, j + 1);
					             }
					         }
					         int exp = 0;
					         if(Integer.parseInt(exponent1, 2)==0) {
					        	 
					        	 exp = (int) -(Integer.parseInt(exponent1, 2)+ Math.pow(2, exponent1.length()-1)-2) ;
					         }
					         else {
					        	 frac+=1;
					        	 exp = (int) (Integer.parseInt(exponent1, 2) - (Math.pow(2, exponent1.length()-1)-1)) ;
					         }
					         
					         double result = (sign.equals("1") ? -1 : 1) * (frac) * Math.pow(2, exp);
					         System.out.print(result +" ");
						}
						hexPiece = "";
					}
					
					break;
				case 4:
					for(int i = 0; i < 3; i++)  {
						hexPiece = currentLine[4*i+3] + "";
						hexPiece += currentLine[4*i+2] + "";	
						hexPiece += currentLine[4*i+1] + "";	
						hexPiece += currentLine[4*i] + "";
						hexPieceArray[i] = hexPiece;
						binaryStr = hexToBinary(hexPieceArray[i]);
					
						if (dataTypeNumber == 1) {
							 if (binaryStr.substring(0,1).equals("1")) {
							String modifiedString = "";
							for (int j = 0; j < binaryStr.length(); j++) {
							     if (binaryStr.charAt(j) == '0') {
							         modifiedString += '1';
							     } else if (binaryStr.charAt(j) == '1') {
							         modifiedString += '0';
							     } else {
							         modifiedString += binaryStr.charAt(j);
							     }
							 }
							long num = Long.parseLong(modifiedString, 2);
							num++;
							String newString = Long.toBinaryString(num);
							System.out.println(-1*Long.parseLong(newString,2));
							 }
							 else {
								 System.out.println(Long.parseLong(binaryStr, 2));
							 }
								 
						}
						else if (dataTypeNumber == 2) {
						System.out.println(Long.parseLong(binaryStr, 2));
						}
						else if (dataTypeNumber == 3) {
							String sign = binaryStr.substring(0, 1);
					         String exponent1 = binaryStr.substring(1, 11);
					         String fraction = binaryStr.substring(11,23);
					          
					         double frac = 0.0;
					         for (int j = 0; j < fraction.length(); j++) {
					             if (fraction.charAt(j) == '1') {
					                 frac += 1.0 / Math.pow(2, j + 1);
					             }
					         }
					         int exp = 0;
					         if(Integer.parseInt(exponent1, 2)==0) {
					        	 
					        	 exp = (int) -(Integer.parseInt(exponent1, 2)+ Math.pow(2, exponent1.length()-1)-2) ;
					         }
					         else {
					        	 frac+=1;
					        	 exp = (int) (Integer.parseInt(exponent1, 2) - (Math.pow(2, exponent1.length()-1)-1)) ;
					         }
					         
					         double result = (sign.equals("1") ? -1 : 1) * (frac) * Math.pow(2, exp);
					         System.out.print(result +" ");
						}
						hexPiece = "";
					}
					break;
				}
				
				
			}
		System.out.println("\n");
		}
		
		
		
	}
}