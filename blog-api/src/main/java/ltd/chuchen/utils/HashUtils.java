package ltd.chuchen.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description: Hash工具类
 * @Author: chuchen
 * @Date: 2020-11-17
 */
public class HashUtils {
	private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public static String getBC(CharSequence rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	public static boolean matchBC(CharSequence rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}

	public static void main(String[] args) {
		System.out.println(getBC("88888888"));
	}
}
