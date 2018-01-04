package solution.day5;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import skeleton.AdventOfCode;

public class Day5 extends AdventOfCode{

	public Day5() throws FileNotFoundException {
		super();
	}

	@Override
	public void part1() {
		String doorId = this.getInput().replace("\n", "");
		byte[] digest;
		StringBuilder hex;
		Formatter format;
		StringBuilder password = new StringBuilder();
		long index = 0;
		String toHash;
		
		try {
			MessageDigest hash = MessageDigest.getInstance("MD5");
			while(password.length() < 8) {
				hex = new StringBuilder();
				format = new Formatter(hex);
				toHash = doorId + index;
				digest = hash.digest(toHash.getBytes());
				
				for(int i=0; i < 3; i++) {
					format.format("%02x", digest[i]);
				}
				if(hex.toString().startsWith("00000")) {
					password.append(hex.charAt(5));
				}
				index++;
			}
			System.out.println(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void part2() {
		String doorId = this.getInput().replace("\n", "");
		byte[] digest;
		StringBuilder hex;
		Formatter format;
		StringBuilder password = new StringBuilder("zzzzzzzz");
		long index = 0;
		String toHash;
		int position;
		try {
			MessageDigest hash = MessageDigest.getInstance("MD5");
			while(password.indexOf("z") != -1) {
				hex = new StringBuilder();
				format = new Formatter(hex);
				toHash = doorId + index;
				digest = hash.digest(toHash.getBytes());
				
				for(int i=0; i < 4; i++) {
					format.format("%02x", digest[i]);
				}
				if(hex.toString().startsWith("00000")) {
					position = (int)hex.charAt(5) - 48;
					
					if(position < 8 && password.charAt(position) == 'z') {
						password.replace(position, position+1, hex.substring(6,7));
					}
					
				}
				index++;
			}
			System.out.println(String.valueOf(password));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	private int convertHexToInt(char hex) {
		if(hex >= '0' && hex <= '9') {
			return (int)hex - 48;
		} else {
			switch(hex) {
			case 'a': return 10;
			case 'b': return 11;
			case 'c': return 12;
			case 'd' : return 13;
			case 'e' : return 14;
			case 'f' : return 15;
			default: return 0;
			}
		}
	}
*/
}
